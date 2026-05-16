package com.bootcamp.demo.bc_mtr_station.cache;

import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.mapper.DtoMapper;
import com.bootcamp.demo.bc_mtr_station.model.RedisKeys;
import com.bootcamp.demo.bc_mtr_station.repository.LineRepository;
import com.bootcamp.demo.bc_mtr_station.repository.LineStationRepository;
import com.bootcamp.demo.bc_mtr_station.repository.StationRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Service
public class StationCacheService {
  @Autowired private StationRepository stationRepository;
  @Autowired private LineRepository lineRepository;
  @Autowired private LineStationRepository lineStationRepository;
  @Autowired private RedisTemplate<String, String> redisTemplate;
  @Autowired private DtoMapper dtoMapper;
  @Autowired private ObjectMapper objectMapper;

  public void refreshFromDb() {
    try {
      // 1. Handle Stations Cache
      List<StationEntity> stations = this.stationRepository.findAll();
      List<Map<String, String>> cachedStations =
          stations.stream().map(s -> Map.of("code", s.getCode(), "name", s.getName())).toList();
      this.redisTemplate
          .opsForValue()
          .set(RedisKeys.STATIONS, this.objectMapper.writeValueAsString(cachedStations));

      // 2. Pre-group stations by line code (Massive speed up)
      List<LineStationEntity> lineStations = this.lineStationRepository.findAll();
      Map<String, List<LineStationEntity>> stationsByLineCode =
          lineStations.stream()
              .filter(ls -> ls.getLineEntity() != null && ls.getLineEntity().getCode() != null)
              .collect(Collectors.groupingBy(ls -> ls.getLineEntity().getCode().toUpperCase()));

      // 3. Map Lines using the pre-grouped data
      List<LineEntity> lines = this.lineRepository.findAll();
      Map<String, CachedLineDto> cachedLines = new HashMap<>();

      for (LineEntity line : lines) {
        String lineCode = line.getCode().toUpperCase();

        // Instantly look up only the stations belonging to this line
        List<LineStationEntity> matchedLineStations =
            stationsByLineCode.getOrDefault(lineCode, List.of());

        List<Map<String, String>> stationsOfLine =
            matchedLineStations.stream()
                .map(
                    ls ->
                        Map.of(
                            "code", ls.getStationEntity().getCode(),
                            "name", ls.getStationEntity().getName()))
                .toList();

        cachedLines.put(line.getCode(), this.dtoMapper.map(line, stationsOfLine));
      }

      this.redisTemplate
          .opsForValue()
          .set(RedisKeys.LINES, this.objectMapper.writeValueAsString(cachedLines));

    } catch (JacksonException e) {
      throw new RuntimeException("Failed to cache stations", e);
    }
  }
}
