package com.bootcamp.demo.bc_mtr_station.controller;

import com.bootcamp.demo.bc_mtr_station.dto.NewStationReq;
import com.bootcamp.demo.bc_mtr_station.dto.TrainDto;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface StationOperation {
  @GetMapping(value = "/stations")
  Map<String, List<StationEntity>> getAllStations();

  @GetMapping(value = "/stations/line")
  List<StationEntity> getStationsByLine(String lineCode);

  @PostMapping(value = "/station")
  StationEntity addStation(@RequestBody NewStationReq newStationReq);

  @DeleteMapping(value = "/station")
  StationEntity deleteStation(String code);

  @GetMapping
  TrainDto getEarliestTrain(String stationCode);
}
