package com.bootcamp.demo.bc_mtr_station.service.impl;

import com.bootcamp.demo.bc_mtr_station.cache.CachedLineDto;
import com.bootcamp.demo.bc_mtr_station.dto.LineSignalDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.exception.DataUnavailableException;
import com.bootcamp.demo.bc_mtr_station.exception.NoSuchLineException;
import com.bootcamp.demo.bc_mtr_station.mapper.DtoMapper;
import com.bootcamp.demo.bc_mtr_station.model.RedisKeys;
import com.bootcamp.demo.bc_mtr_station.model.Signal;
import com.bootcamp.demo.bc_mtr_station.model.TrainTimeDTO;
import com.bootcamp.demo.bc_mtr_station.repository.LineRepository;
import com.bootcamp.demo.bc_mtr_station.service.LineService;
import com.bootcamp.demo.bc_mtr_station.service.StationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class LineServiceImpl implements LineService {
  private final DtoMapper dtoMapper;
  @Autowired private LineRepository lineRepository;
  @Autowired private StationService stationService;
  @Autowired private RedisTemplate<String, String> redisTemplate;
  @Autowired private ObjectMapper objectMapper;

  LineServiceImpl(DtoMapper dtoMapper) {
    this.dtoMapper = dtoMapper;
  }

  @Override
  public List<LineEntity> getAllLines() {
    return this.lineRepository.findAll();
  }

  @Override
  public LineSignalDto getLineSignal(String lineCode) {
    String normalizedCode = lineCode.toUpperCase();
    String lineJson = this.redisTemplate.opsForValue().get(RedisKeys.LINES);

    if (lineJson != null) {
      if (!lineJson.contains(normalizedCode)) {
        throw new NoSuchLineException();
      }
    } else {
      if (!this.lineRepository.findByCode(normalizedCode).isPresent()) {
        throw new NoSuchLineException();
      }
    }
    return this.lineSignal(normalizedCode);
  }

  @Override
  public List<LineSignalDto> getAllLineSignals() {
    String lineJson = this.redisTemplate.opsForValue().get(RedisKeys.LINES);
    if (lineJson == null) {
      return this.lineRepository.findAll().stream()
          .map(LineEntity::getCode)
          .toList()
          .parallelStream()
          .map(this::lineSignal)
          .toList();
    } else {
      try {
        Map<String, CachedLineDto> cachedlines =
            this.objectMapper.readValue(
                lineJson, new TypeReference<Map<String, CachedLineDto>>() {});
        // ! can use stream/parellelStream directly on keySet
        return cachedlines.keySet().parallelStream().map(this::lineSignal).toList();

      } catch (JacksonException e) {
        throw new RuntimeException("Failed to parse cached lines", e);
      }
    }
  }

  private LineSignalDto lineSignal(String lineCode) {
    List<String> matchedStations =
        this.stationService.getStationsByLine(lineCode).stream().map(s -> s.getCode()).toList();

    List<String> delaystation = new CopyOnWriteArrayList<>();

    // 💡 Pair the station code with its TrainTimeDTO using Map.entry(key, value)
    List<Map.Entry<String, TrainTimeDTO>> stationSchedulePairs =
        matchedStations.parallelStream()
            .map(
                station -> {
                  try {
                    TrainTimeDTO dto = this.stationService.getTrainSchedule(lineCode, station);
                    return dto != null ? Map.entry(station, dto) : null;
                  } catch (Exception e) {
                    throw new DataUnavailableException();
                  }
                })
            .filter(Objects::nonNull)
            .toList();

    String currentTime = "";
    String systemTime = "";

    for (Map.Entry<String, TrainTimeDTO> pair : stationSchedulePairs) {
      String station = pair.getKey();
      TrainTimeDTO trainTimeDTO = pair.getValue();

      if (currentTime.isBlank()) {
        currentTime = trainTimeDTO.getCurrTime();
        systemTime = trainTimeDTO.getSysTime();
      }

      if ("Y".equalsIgnoreCase(trainTimeDTO.getIsdelay())) {
        delaystation.add(station);
      }
    }

    Signal signal = null;
    if (delaystation.isEmpty()) {
      signal = Signal.GREEN;
    } else if (delaystation.size() == 1) {
      signal = Signal.YELLOW;
    } else {
      signal = Signal.RED;
    }

    return this.dtoMapper.mapSignal(
        currentTime, systemTime, lineCode, signal, new ArrayList<>(delaystation));
  }
}
