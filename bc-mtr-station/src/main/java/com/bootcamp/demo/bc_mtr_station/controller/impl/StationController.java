package com.bootcamp.demo.bc_mtr_station.controller.impl;

import com.bootcamp.demo.bc_mtr_station.controller.StationOperation;
import com.bootcamp.demo.bc_mtr_station.dto.DepartureDto;
import com.bootcamp.demo.bc_mtr_station.dto.NewStationReq;
import com.bootcamp.demo.bc_mtr_station.dto.StationResDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.model.TrainTimeDTO;
import com.bootcamp.demo.bc_mtr_station.service.StationService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationController implements StationOperation {
  @Autowired StationService stationService;

  @Override
  public Map<String, List<StationResDto>> getAllStations() {
    return this.stationService.getAllStations();
  }

  @Override
  public List<StationResDto> getStationsByLine(String lineCode) {
    return this.stationService.getStationsByLine(lineCode);
  }

  @Override
  public LineStationEntity addStation(NewStationReq newStationReq) {
    return this.stationService.addStation(newStationReq);
  }

  @Override
  public StationEntity deleteStation(String code) {
    return this.stationService.deleteStation(code);
  }

  @Override
  public TrainTimeDTO getTrainSchedule(String lineCode, String stationCode) {
    return this.stationService.getTrainSchedule(lineCode, stationCode);
  }

  @Override
  public DepartureDto getEarliestTrain(String lineCode, String stationCode) {
    return this.stationService.getEarliestTrain(lineCode, stationCode);
  }
}
