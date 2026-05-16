package com.bootcamp.demo.bc_mtr_station.service;

import com.bootcamp.demo.bc_mtr_station.dto.DepartureDto;
import com.bootcamp.demo.bc_mtr_station.dto.NewStationReq;
import com.bootcamp.demo.bc_mtr_station.dto.StationResDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.model.TrainTimeDTO;
import java.util.List;
import java.util.Map;

public interface StationService {
  Map<String, List<StationResDto>> getAllStations();

  List<StationResDto> getStationsByLine(String lineCode);

  LineStationEntity addStation(NewStationReq newStationReq);

  StationEntity deleteStation(String code);

  TrainTimeDTO getTrainSchedule(String lineCode, String stationCode);

  DepartureDto getEarliestTrain(String lineCode, String stationCode);
}
