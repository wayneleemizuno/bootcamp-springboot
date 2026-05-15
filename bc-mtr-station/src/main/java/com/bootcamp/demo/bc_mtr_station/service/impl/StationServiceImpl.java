package com.bootcamp.demo.bc_mtr_station.service.impl;

import com.bootcamp.demo.bc_mtr_station.config.AppConfig;
import com.bootcamp.demo.bc_mtr_station.dto.DepartureDto;
import com.bootcamp.demo.bc_mtr_station.dto.NewStationReq;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.exception.DataUnavailableException;
import com.bootcamp.demo.bc_mtr_station.exception.NoSuchLineException;
import com.bootcamp.demo.bc_mtr_station.exception.NoSuchStationException;
import com.bootcamp.demo.bc_mtr_station.mapper.DtoMapper;
import com.bootcamp.demo.bc_mtr_station.mapper.EntityMapper;
import com.bootcamp.demo.bc_mtr_station.model.TrainTimeDTO;
import com.bootcamp.demo.bc_mtr_station.model.TrainTimeDTO.DepartureDetails;
import com.bootcamp.demo.bc_mtr_station.repository.LineRepository;
import com.bootcamp.demo.bc_mtr_station.repository.LineStationRepository;
import com.bootcamp.demo.bc_mtr_station.repository.StationRepository;
import com.bootcamp.demo.bc_mtr_station.service.StationService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class StationServiceImpl implements StationService {
  @Autowired private LineRepository lineRepository;
  @Autowired private StationRepository stationRepository;
  @Autowired private LineStationRepository lineStationRepository;
  @Autowired private EntityMapper entityMapper;
  @Autowired private AppConfig appConfig;
  @Autowired private RestTemplate restTemplate;
  @Autowired private DtoMapper dtoMapper;
  @Autowired private RedisTemplate<String, String> redisTemplate;

  @Value("${api.gov.domain}")
  private String domain;

  @Value("${api.gov.version}")
  private String ver;

  @Value("${api.gov.mtr.schedule}")
  private String path;

  @Override
  public Map<String, List<StationEntity>> getAllStations() {
    List<LineEntity> lines = this.lineRepository.findAll();
    Map<String, List<StationEntity>> stationMap = new HashMap<String, List<StationEntity>>();
    lines.stream()
        .forEach(
            line -> {
              List<StationEntity> matchedStations = this.getStationsByLine(line.getCode());
              stationMap.put(line.getName(), matchedStations);
            });
    return stationMap;
  }

  // TD: Re-write service - cache (redis)
  @Override
  public List<StationEntity> getStationsByLine(String lineCode) {
    if (!this.lineRepository.findByCode(lineCode.toUpperCase()).isPresent())
      throw new NoSuchLineException();
    return this.lineStationRepository.findAll().stream()
        .filter(ls -> ls.getLineEntity().getCode().equalsIgnoreCase(lineCode))
        .map(ls -> ls.getStationEntity())
        .collect(Collectors.toList());
  }

  @Override
  public LineStationEntity addStation(NewStationReq newStationReq) {
    if (!this.lineRepository.findByCode(newStationReq.getLineCode().toUpperCase()).isPresent())
      throw new NoSuchLineException();

    StationEntity newStation =
        this.entityMapper.map(newStationReq.getCode(), newStationReq.getName());

    LineEntity targetLine =
        this.lineRepository.findByCode(newStationReq.getLineCode().toUpperCase()).get();

    this.stationRepository.save(newStation);
    return this.lineStationRepository.save(this.entityMapper.map(targetLine, newStation));
  }

  @Override
  @Transactional
  public StationEntity deleteStation(String code) {
    if (!this.stationRepository.findByCode(code.toUpperCase()).isPresent())
      throw new NoSuchStationException();

    StationEntity target = this.stationRepository.findByCode(code.toUpperCase()).get();
    this.lineStationRepository.deleteByStationEntity(target);
    this.stationRepository.deleteByCode(code.toUpperCase());
    return target;
  }

  @Override
  public TrainTimeDTO getTrainSchedule(String lineCode, String stationCode) {
    if (!this.lineRepository.findByCode(lineCode.toUpperCase()).isPresent()) {
      throw new NoSuchLineException();
    } else {
      if (!this.stationRepository.findByCode(stationCode.toUpperCase()).isPresent())
        throw new NoSuchStationException();
    }

    String url =
        UriComponentsBuilder.newInstance()
            .scheme("https")
            .host(this.domain)
            .pathSegment(this.ver)
            .path(this.path)
            .queryParam("line", lineCode.toUpperCase())
            .queryParam("sta", stationCode.toUpperCase())
            .build()
            .toUriString();
    return this.restTemplate.getForObject(url, TrainTimeDTO.class);
  }

  @Override
  public DepartureDto getEarliestTrain(String lineCode, String stationCode) {
    if (!this.lineRepository.findByCode(lineCode.toUpperCase()).isPresent()) {
      throw new NoSuchLineException();
    } else {
      if (!this.stationRepository.findByCode(stationCode.toUpperCase()).isPresent())
        throw new NoSuchStationException();
    }

    TrainTimeDTO trainTimeDTO =
        this.getTrainSchedule(lineCode.toUpperCase(), stationCode.toUpperCase());

    if (trainTimeDTO.getData() == null) throw new DataUnavailableException();

    List<DepartureDto.Train> earliestTrains = new ArrayList<>();
    List<DepartureDetails> up =
        trainTimeDTO
            .getData()
            .get(lineCode.toUpperCase() + "-" + stationCode.toUpperCase())
            .getUpDepartures();

    List<DepartureDetails> down =
        trainTimeDTO
            .getData()
            .get(lineCode.toUpperCase() + "-" + stationCode.toUpperCase())
            .getDownDepartures();

    HashSet<String> destinations =
        (HashSet<String>)
            Stream.concat(
                    up == null ? Stream.empty() : up.stream(),
                    down == null ? Stream.empty() : down.stream())
                .collect(Collectors.toList())
                .stream()
                .map(e -> e.getDest())
                .collect(Collectors.toCollection(HashSet::new));

    if (up != null)
      destinations.stream()
          .forEach(
              dest -> {
                List<DepartureDetails> Departures =
                    up.stream()
                        .filter(dep -> dep.getDest().equals(dest))
                        .sorted(Comparator.comparing(DepartureDetails::getTime))
                        .collect(Collectors.toList());
                if (!Departures.isEmpty())
                  earliestTrains.add(this.dtoMapper.mapFirstUpTrain(Departures.get(0), dest));
              });

    if (down != null)
      destinations.stream()
          .forEach(
              dest -> {
                List<DepartureDetails> Departures =
                    down.stream()
                        .filter(dep -> dep.getDest().equalsIgnoreCase(dest))
                        .sorted(Comparator.comparing(DepartureDetails::getTime))
                        .collect(Collectors.toList());
                if (!Departures.isEmpty())
                  earliestTrains.add(this.dtoMapper.mapFirstDownTrain(Departures.get(0), dest));
              });

    return this.dtoMapper.map(trainTimeDTO, stationCode, earliestTrains);
  }
}
