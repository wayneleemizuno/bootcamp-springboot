package com.bootcamp.demo.bc_mtr_station.mapper;

import com.bootcamp.demo.bc_mtr_station.dto.DepartureDto;
import com.bootcamp.demo.bc_mtr_station.model.TrainTimeDTO;
import com.bootcamp.demo.bc_mtr_station.model.TrainTimeDTO.DepartureDetails;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
  public DepartureDto map(
      TrainTimeDTO trainTimeDTO, String stationCode, List<DepartureDto.Train> trains) {
    return DepartureDto.builder()
        .currentTime(trainTimeDTO.getCurrTime())
        .systemTime(trainTimeDTO.getSysTime())
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
}
