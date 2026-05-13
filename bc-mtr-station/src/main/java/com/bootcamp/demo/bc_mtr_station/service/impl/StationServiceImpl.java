package com.bootcamp.demo.bc_mtr_station.service.impl;

import com.bootcamp.demo.bc_mtr_station.dto.TrainDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.repository.LineRepository;
import com.bootcamp.demo.bc_mtr_station.repository.LineStationRepository;
import com.bootcamp.demo.bc_mtr_station.repository.StationRepository;
import com.bootcamp.demo.bc_mtr_station.service.StationService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {
  @Autowired LineRepository lineRepository;
  @Autowired StationRepository stationRepository;
  @Autowired LineStationRepository lineStationRepository;

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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getStationsByLine'");
  }

  @Override
  public StationEntity addStation() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addStation'");
  }

  @Override
  public StationEntity deleteStation(String code) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteStation'");
  }

  @Override
  public TrainDto getEarliestTrain(String stationCode) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEarliestTrain'");
  }
}
