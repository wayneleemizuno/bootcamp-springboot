package com.bootcamp.demo.bc_mtr_station.controller;

import com.bootcamp.demo.bc_mtr_station.dto.DepartureDto;
import com.bootcamp.demo.bc_mtr_station.dto.NewStationReq;
import com.bootcamp.demo.bc_mtr_station.dto.StationResDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.model.TrainTimeDTO;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface StationOperation {
  @GetMapping(value = "/stations")
  Map<String, List<StationResDto>> getAllStations();

  @GetMapping(value = "/stations/line")
  List<StationResDto> getStationsByLine(@RequestParam String lineCode);

  @PostMapping(value = "/station")
  LineStationEntity addStation(@RequestBody NewStationReq newStationReq);

  @DeleteMapping(value = "/station")
  StationEntity deleteStation(@RequestParam String code);

  @GetMapping(value = "/train_schedule")
  TrainTimeDTO getTrainSchedule(String lineCode, String stationCode);

  @GetMapping(value = "/first_dep")
  DepartureDto getEarliestTrain(@RequestParam String stationCode);
}
