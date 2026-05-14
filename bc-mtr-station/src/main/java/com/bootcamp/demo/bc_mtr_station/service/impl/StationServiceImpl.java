package com.bootcamp.demo.bc_mtr_station.service.impl;

import com.bootcamp.demo.bc_mtr_station.config.AppConfig;
import com.bootcamp.demo.bc_mtr_station.dto.NewStationReq;
import com.bootcamp.demo.bc_mtr_station.dto.TrainDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.exception.DataUnavailableException;
import com.bootcamp.demo.bc_mtr_station.mapper.DtoMapper;
import com.bootcamp.demo.bc_mtr_station.mapper.EntityMapper;
import com.bootcamp.demo.bc_mtr_station.model.TrainTimeDTO;
import com.bootcamp.demo.bc_mtr_station.model.TrainTimeDTO.DepartureDetails;
import com.bootcamp.demo.bc_mtr_station.repository.LineRepository;
import com.bootcamp.demo.bc_mtr_station.repository.LineStationRepository;
import com.bootcamp.demo.bc_mtr_station.repository.StationRepository;
import com.bootcamp.demo.bc_mtr_station.service.StationService;
import jakarta.transaction.Transactional;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StationServiceImpl implements StationService {
  @Autowired LineRepository lineRepository;
  @Autowired StationRepository stationRepository;
  @Autowired LineStationRepository lineStationRepository;
  @Autowired EntityMapper entityMapper;
  @Autowired AppConfig appConfig;
  @Autowired RestTemplate restTemplate;
  @Autowired DtoMapper dtoMapper;

  @Override
  public Map<String, List<StationEntity>> getAllStations() {
    List<LineEntity> lines = this.lineRepository.findAll();
    List<LineStationEntity> lineStationEntities = this.lineStationRepository.findAll();
    Map<String, List<StationEntity>> stationMap = new HashMap<String, List<StationEntity>>();
    lines.stream()
        .forEach(
            line -> {
              List<StationEntity> matchedStations =
                  lineStationEntities.stream()
                      .filter(ls -> ls.getLineEntity().getCode().equals(line.getCode()))
                      .map(ls -> ls.getStationEntity())
                      .collect(Collectors.toList());
              stationMap.put(line.getName(), matchedStations);
            });
    return stationMap;
  }

  @Override
  public List<StationEntity> getStationsByLine(String lineCode) {
    return this.lineStationRepository.findAll().stream()
        .filter(ls -> ls.getLineEntity().getCode().toLowerCase().equals(lineCode.toLowerCase()))
        .map(ls -> ls.getStationEntity())
        .collect(Collectors.toList());
  }

  @Override
  public LineStationEntity addStation(NewStationReq newStationReq) {
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
    StationEntity target = this.stationRepository.findByCode(code).get();
    this.lineStationRepository.deleteByStationEntity(target);
    this.stationRepository.deleteByCode(code);
    return target;
  }

  @Override
  public TrainTimeDTO getTrainSchedule(String lineCode, String stationCode) {
    String url =
        "https://rt.data.gov.hk/v1/transport/mtr/getSchedule.php?line="
            + lineCode.toUpperCase()
            + "&sta="
            + stationCode.toUpperCase();
    System.out.println("URL" + url);
    return this.restTemplate.getForObject(url, TrainTimeDTO.class);
  }

  @Override
  public TrainDto getEarliestTrain(String lineCode, String stationCode) {
    if (this.getTrainSchedule(lineCode, stationCode).getData() == null)
      throw new DataUnavailableException();

    List<DepartureDetails> up =
        this.getTrainSchedule(lineCode, stationCode)
            .getData()
            .get(lineCode.toUpperCase() + "-" + stationCode.toUpperCase())
            .getUpDepartures();

    List<DepartureDetails> down =
        this.getTrainSchedule(lineCode, stationCode)
            .getData()
            .get(lineCode.toUpperCase() + "-" + stationCode.toUpperCase())
            .getDownDepartures();

    TrainTimeDTO.DepartureDetails firstDept =
        Stream.concat(
                up == null ? Stream.empty() : up.stream(),
                down == null ? Stream.empty() : down.stream())
            .sorted(Comparator.comparing(DepartureDetails::getTime))
            .collect(Collectors.toList())
            .getFirst();

    return this.dtoMapper.map(firstDept, stationCode);
  }
}
