package com.bootcamp.demo.bc_mtr_station.mapper;

import com.bootcamp.demo.bc_mtr_station.cache.CachedLineDto;
import com.bootcamp.demo.bc_mtr_station.dto.DepartureDto;
import com.bootcamp.demo.bc_mtr_station.dto.LineSignalDto;
import com.bootcamp.demo.bc_mtr_station.dto.StationResDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.model.Signal;
import com.bootcamp.demo.bc_mtr_station.model.TrainTimeDTO.DepartureDetails;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
  public DepartureDto map(
      String currTime, String sysTime, String stationCode, List<DepartureDto.Train> trains) {
    return DepartureDto.builder()
        .currentTime(currTime)
        .systemTime(sysTime)
        .currentStation(stationCode.toUpperCase())
        .trains(trains)
        .build();
  }

  public DepartureDto.Train mapFirstUpTrain(DepartureDetails departureDetails, String dest) {
    return DepartureDto.Train.builder()
        .destination(dest)
        .arrivalTime(departureDetails.getTime())
        .direction("UP")
        .build();
  }

  public DepartureDto.Train mapFirstDownTrain(DepartureDetails departureDetails, String dest) {
    return DepartureDto.Train.builder()
        .destination(dest)
        .arrivalTime(departureDetails.getTime())
        .direction("DOWN")
        .build();
  }

  public LineSignalDto mapSignal(
      String currTime, String sysTime, String lineCode, Signal signal, List<String> delayStations) {
    return LineSignalDto.builder()
        .lineCode(lineCode.toUpperCase())
        .signal(signal)
        .delayStations(delayStations)
        .currentTime(currTime)
        .systemTime(sysTime)
        .build();
  }

  public CachedLineDto map(LineEntity lineEntity, List<Map<String, String>> stationsOfLine) {
    return CachedLineDto.builder().lineName(lineEntity.getName()).stations(stationsOfLine).build();
  }

  public StationResDto map(StationEntity entity) {
    if (entity == null) {
      return null;
    }
    return StationResDto.builder().code(entity.getCode()).name(entity.getName()).build();
  }

  public StationResDto map(Map<String, String> cachedStation) {
    if (cachedStation == null) {
      return null;
    }
    return StationResDto.builder()
        .code(cachedStation.get("code"))
        .name(cachedStation.get("name"))
        .build();
  }
}
