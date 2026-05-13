package com.bootcamp.demo.bc_mtr_station.controller.impl;

import com.bootcamp.demo.bc_mtr_station.controller.StationOperation;
import com.bootcamp.demo.bc_mtr_station.dto.NewStationReq;
import com.bootcamp.demo.bc_mtr_station.dto.TrainDto;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.service.StationService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationController implements StationOperation {
  @Autowired StationService stationService;

  @Override
  public Map<String, List<StationEntity>> getAllStations() {
    return this.stationService.getAllStations();
  }

  @Override
  public List<StationEntity> getStationsByLine(String lineCode) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getStationsByLine'");
  }

  @Override
  public StationEntity addStation(NewStationReq newStationReq) {
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
