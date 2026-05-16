package com.bootcamp.demo.bc_mtr_station.service.impl;

import com.bootcamp.demo.bc_mtr_station.cache.CachedLineDto;
import com.bootcamp.demo.bc_mtr_station.cache.StationCacheService;
import com.bootcamp.demo.bc_mtr_station.dto.DepartureDto;
import com.bootcamp.demo.bc_mtr_station.dto.NewStationReq;
import com.bootcamp.demo.bc_mtr_station.dto.StationResDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.exception.DataUnavailableException;
import com.bootcamp.demo.bc_mtr_station.exception.NoSuchLineException;
import com.bootcamp.demo.bc_mtr_station.exception.NoSuchStationException;
import com.bootcamp.demo.bc_mtr_station.mapper.DtoMapper;
import com.bootcamp.demo.bc_mtr_station.mapper.EntityMapper;
import com.bootcamp.demo.bc_mtr_station.model.RedisKeys;
import com.bootcamp.demo.bc_mtr_station.model.TrainTimeDTO;
import com.bootcamp.demo.bc_mtr_station.repository.LineRepository;
import com.bootcamp.demo.bc_mtr_station.repository.LineStationRepository;
import com.bootcamp.demo.bc_mtr_station.repository.StationRepository;
import com.bootcamp.demo.bc_mtr_station.service.StationService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class StationServiceImpl implements StationService {
  @Autowired private LineRepository lineRepository;
  @Autowired private StationRepository stationRepository;
  @Autowired private LineStationRepository lineStationRepository;
  @Autowired private EntityMapper entityMapper;
  @Autowired private RestTemplate restTemplate;
  @Autowired private DtoMapper dtoMapper;
  @Autowired private RedisTemplate<String, String> redisTemplate;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private StationCacheService stationCacheService;

  @Value("${api.gov.domain}")
  private String domain;

  @Value("${api.gov.version}")
  private String ver;

  @Value("${api.gov.mtr.schedule}")
  private String path;

  @Override
  public Map<String, List<StationResDto>> getAllStations() {
    Map<String, List<StationResDto>> stationMap = new HashMap<>();

    String lineJson = this.redisTemplate.opsForValue().get(RedisKeys.LINES);

    if (lineJson == null) {
      this.lineRepository.findAll().stream()
          .forEach(
              line -> {
                List<StationResDto> matchedStations = this.getStationsByLine(line.getCode());
                stationMap.put(line.getName(), matchedStations);
              });
      return stationMap;
    } else {
      try {
        Map<String, CachedLineDto> cachedlines =
            this.objectMapper.readValue(
                lineJson, new TypeReference<Map<String, CachedLineDto>>() {});

        for (CachedLineDto dto : cachedlines.values()) {
          List<StationResDto> stations =
              dto.getStations().stream().map(this.dtoMapper::map).toList();
          stationMap.put(dto.getLineName(), stations);
        }
        return stationMap;

      } catch (JacksonException e) {
        throw new RuntimeException("Failed to parse cached lines", e);
      }
    }
  }

  @Override
  public List<StationResDto> getStationsByLine(String lineCode) {
    String lineJson = this.redisTemplate.opsForValue().get(RedisKeys.LINES);

    if (lineJson == null) {
      if (!this.lineRepository.findByCode(lineCode.toUpperCase()).isPresent()) {
        throw new NoSuchLineException();
      } else {
        return this.lineStationRepository.findAllByLineEntityCodeIgnoreCase(lineCode).stream()
            .map(ls -> this.dtoMapper.map(ls.getStationEntity()))
            .toList();
      }

    } else {
      if (!lineJson.contains(lineCode.toUpperCase())) {
        throw new NoSuchLineException();
      } else {
        try {
          Map<String, CachedLineDto> cachedlines =
              this.objectMapper.readValue(
                  lineJson, new TypeReference<Map<String, CachedLineDto>>() {});

          return cachedlines.get(lineCode.toUpperCase()).getStations().stream()
              .map(this.dtoMapper::map)
              .toList();

        } catch (JacksonException e) {
          throw new RuntimeException("Failed to parse cached lines", e);
        }
      }
    }
  }

  @Override
  @Transactional
  public LineStationEntity addStation(NewStationReq newStationReq) {
    if (!this.lineRepository.findByCode(newStationReq.getLineCode().toUpperCase()).isPresent()) {
      throw new NoSuchLineException();
    }

    StationEntity newStation =
        this.entityMapper.map(newStationReq.getCode(), newStationReq.getName());
    LineEntity targetLine =
        this.lineRepository.findByCode(newStationReq.getLineCode().toUpperCase()).get();

    this.stationRepository.save(newStation);
    LineStationEntity lineStationEntity =
        this.lineStationRepository.save(this.entityMapper.map(targetLine, newStation));

    // Register a callback to refresh the cache ONLY after the DB transaction completely commits
    TransactionSynchronizationManager.registerSynchronization(
        new TransactionSynchronization() {
          @Override
          public void afterCommit() {
            stationCacheService.refreshFromDb();
          }
        });

    return lineStationEntity;
  }

  @Override
  @Transactional
  public StationEntity deleteStation(String code) {
    if (!this.stationRepository.findByCode(code.toUpperCase()).isPresent()) {
      throw new NoSuchStationException();
    }
    StationEntity target = this.stationRepository.findByCode(code.toUpperCase()).get();

    this.lineStationRepository.deleteByStationEntity(target);
    this.stationRepository.deleteByCode(code.toUpperCase());

    // Register a callback to refresh the cache ONLY after the DB transaction completely commits
    TransactionSynchronizationManager.registerSynchronization(
        new TransactionSynchronization() {
          @Override
          public void afterCommit() {
            stationCacheService.refreshFromDb();
          }
        });
    return target;
  }

  @Override
  public TrainTimeDTO getTrainSchedule(String lineCode, String stationCode) {
    String lineJson = this.redisTemplate.opsForValue().get(RedisKeys.LINES);
    if (lineJson == null) {
      if (!this.lineRepository.findByCode(lineCode.toUpperCase()).isPresent()) {
        throw new NoSuchLineException();
      } else {
        if (!this.stationRepository.findByCode(stationCode.toUpperCase()).isPresent())
          throw new NoSuchStationException();
      }
    } else {
      if (!lineJson.contains(lineCode.toUpperCase())) {
        throw new NoSuchLineException();
      } else {
        String stationsJson = this.redisTemplate.opsForValue().get(RedisKeys.STATIONS);
        if (stationsJson != null && !stationsJson.contains(stationCode.toUpperCase()))
          throw new NoSuchStationException();
      }
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
    String normalizedLine = lineCode.toUpperCase();
    String normalizedStation = stationCode.toUpperCase();

    TrainTimeDTO trainTimeDTO = this.getTrainSchedule(normalizedLine, normalizedStation);
    if (trainTimeDTO == null || trainTimeDTO.getData() == null) {
      throw new DataUnavailableException();
    }
    String scheduleKey = normalizedLine + "-" + normalizedStation;
    TrainTimeDTO.StationSchedule scheduleData = trainTimeDTO.getData().get(scheduleKey);
    if (scheduleData == null) {
      throw new DataUnavailableException();
    }
    List<TrainTimeDTO.DepartureDetails> up = scheduleData.getUpDepartures();
    List<TrainTimeDTO.DepartureDetails> down = scheduleData.getDownDepartures();
    List<DepartureDto.Train> earliestTrains = new ArrayList<>();

    if (up != null) {
      up.stream()
          .collect(
              Collectors.groupingBy(
                  dep -> dep.getDest(),
                  Collectors.minBy(Comparator.comparing(TrainTimeDTO.DepartureDetails::getTime))))
          .forEach(
              (dest, optionalDep) ->
                  optionalDep.ifPresent(
                      dep -> earliestTrains.add(this.dtoMapper.mapFirstUpTrain(dep, dest))));
    }
    if (down != null) {
      down.stream()
          .collect(
              Collectors.groupingBy(
                  dep -> dep.getDest(),
                  Collectors.minBy(Comparator.comparing(TrainTimeDTO.DepartureDetails::getTime))))
          .forEach(
              (dest, optionalDep) ->
                  optionalDep.ifPresent(
                      dep -> earliestTrains.add(this.dtoMapper.mapFirstDownTrain(dep, dest))));
    }
    return this.dtoMapper.map(trainTimeDTO, normalizedStation, earliestTrains);
  }
}
