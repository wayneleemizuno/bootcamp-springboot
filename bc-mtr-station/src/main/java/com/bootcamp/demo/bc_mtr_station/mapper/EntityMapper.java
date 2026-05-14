package com.bootcamp.demo.bc_mtr_station.mapper;

import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {
  public StationEntity map(String code, String name) {
    return StationEntity.builder().code(code.toUpperCase()).name(name).build();
  }

  public LineStationEntity map(LineEntity lineEntity, StationEntity stationEntity) {
    return LineStationEntity.builder().lineEntity(lineEntity).stationEntity(stationEntity).build();
  }
}
