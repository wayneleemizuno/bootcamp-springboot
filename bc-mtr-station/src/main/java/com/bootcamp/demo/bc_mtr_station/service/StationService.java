package com.bootcamp.demo.bc_mtr_station.service;

import com.bootcamp.demo.bc_mtr_station.dto.TrainDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import java.util.List;
import java.util.Map;

public interface StationService {
  StationEntity createStation(String code, String name);

  Map<LineEntity, List<StationEntity>> getAllStations();

  List<StationEntity> getStationsByLine(String lineCode);

  StationEntity addStation();

  StationEntity deleteStation(String code);

  TrainDto getEarliestTrain(String stationCode);
}
