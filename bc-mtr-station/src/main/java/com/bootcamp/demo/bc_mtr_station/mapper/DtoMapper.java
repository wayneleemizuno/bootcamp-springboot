package com.bootcamp.demo.bc_mtr_station.mapper;

import com.bootcamp.demo.bc_mtr_station.dto.TrainDto;
import com.bootcamp.demo.bc_mtr_station.model.TrainTimeDTO;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
  public TrainDto map(TrainTimeDTO.DepartureDetails scheduleDetails, String stationCode) {
    return TrainDto.builder()
        .stationCode(stationCode.toUpperCase())
        .ttnt(Integer.valueOf(scheduleDetails.getTtnt()))
        .plat(scheduleDetails.getPlat())
        .time(scheduleDetails.getTime())
        .dest(scheduleDetails.getDest())
        .build();
  }
}
