package com.bootcamp.demo.bc_mtr_station.service.impl;

import com.bootcamp.demo.bc_mtr_station.cache.StationCacheService;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.mapper.EntityMapper; // 💡 Injected Mapper
import com.bootcamp.demo.bc_mtr_station.model.MtrLine;
import com.bootcamp.demo.bc_mtr_station.model.MtrStation;
import com.bootcamp.demo.bc_mtr_station.repository.LineRepository;
import com.bootcamp.demo.bc_mtr_station.repository.LineStationRepository;
import com.bootcamp.demo.bc_mtr_station.repository.StationRepository;
import com.bootcamp.demo.bc_mtr_station.service.DbInitService;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbInitServiceImpl implements DbInitService {

  @Autowired private LineRepository lineRepository;
  @Autowired private StationRepository stationRepository;
  @Autowired private LineStationRepository lineStationRepository;
  @Autowired private StationCacheService stationCacheService;
  @Autowired private EntityMapper entityMapper;

  @Override
  @Transactional
  public void resetAndSeedDatabase() {
    this.lineStationRepository.deleteAll();
    this.lineRepository.deleteAll();
    this.stationRepository.deleteAll();

    // Loop Enums and map via EntityMapper
    Map<MtrLine, LineEntity> lineEntityMap =
        Arrays.stream(MtrLine.values())
            .collect(
                Collectors.toMap(
                    line -> line,
                    line ->
                        this.lineRepository.save(
                            this.entityMapper.mapLine(line.name(), line.getName()))));

    // Loop Enums and map via EntityMapper
    Map<MtrStation, StationEntity> stationEntityMap =
        Arrays.stream(MtrStation.values())
            .collect(
                Collectors.toMap(
                    station -> station,
                    station ->
                        this.stationRepository.save(
                            this.entityMapper.map(station.name(), station.getName()))));

    // Flatten relationships and build bridge entities using EntityMapper
    List<LineStationEntity> relations =
        Arrays.stream(MtrStation.values())
            .flatMap(
                stationEnum ->
                    stationEnum.getLines().stream()
                        .map(
                            lineEnum ->
                                this.entityMapper.map(
                                    lineEntityMap.get(lineEnum),
                                    stationEntityMap.get(stationEnum))))
            .toList();

    this.lineStationRepository.saveAll(relations);

    this.stationCacheService.refreshFromDb();
  }
}
