package com.bootcamp.demo.bc_mtr_station.service.impl;

import com.bootcamp.demo.bc_mtr_station.dto.LineSignalDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.exception.NoSuchLineException;
import com.bootcamp.demo.bc_mtr_station.mapper.DtoMapper;
import com.bootcamp.demo.bc_mtr_station.model.Signal;
import com.bootcamp.demo.bc_mtr_station.model.TrainTimeDTO;
import com.bootcamp.demo.bc_mtr_station.repository.LineRepository;
import com.bootcamp.demo.bc_mtr_station.repository.LineStationRepository;
import com.bootcamp.demo.bc_mtr_station.repository.StationRepository;
import com.bootcamp.demo.bc_mtr_station.service.LineService;
import com.bootcamp.demo.bc_mtr_station.service.StationService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LineServiceImpl implements LineService {
  private final DtoMapper dtoMapper;
  @Autowired LineRepository lineRepository;
  @Autowired StationRepository stationRepository;
  @Autowired LineStationRepository lineStationRepository;
  @Autowired RestTemplate restTemplate;
  @Autowired StationService stationService;

  LineServiceImpl(DtoMapper dtoMapper) {
    this.dtoMapper = dtoMapper;
  }

  @Override
  public List<LineEntity> getAllLines() {
    return this.lineRepository.findAll();
  }

  @Override
  public LineSignalDto getLineSignal(String lineCode) {
    if (!this.lineRepository.findByCode(lineCode.toUpperCase()).isPresent())
      throw new NoSuchLineException();
    return this.lineSignal(lineCode);
  }

  @Override
  public List<LineSignalDto> getAllLineSignals() {
    List<String> lines =
        this.lineRepository.findAll().stream()
            .map(line -> line.getCode())
            .collect(Collectors.toList());

    return lines.stream().map(line -> this.lineSignal(line)).collect(Collectors.toList());
  }

  private LineSignalDto lineSignal(String lineCode) {
    List<String> matchedStations =
        this.stationService.getStationsByLine(lineCode.toUpperCase()).stream()
            .map(s -> s.getCode())
            .collect(Collectors.toList());

    List<String> delaystation = new ArrayList<>();
    String currentTime = "";
    String systemTime = "";

    for (String station : matchedStations) {
      TrainTimeDTO trainTimeDTO = this.stationService.getTrainSchedule(lineCode, station);
      if (currentTime.isBlank()) {
        currentTime = trainTimeDTO.getCurrTime();
        systemTime = trainTimeDTO.getSysTime();
      }
      if ("Y".equalsIgnoreCase(trainTimeDTO.getIsdelay())) {
        delaystation.add(station);
      }
    }

    Signal signal = null;
    if (delaystation.size() == 0) {
      signal = Signal.GREEN;
    } else if (delaystation.size() == 1) {
      signal = Signal.YELLOW;
    } else {
      signal = Signal.RED;
    }

    return this.dtoMapper.mapSignal(currentTime, systemTime, lineCode, signal, delaystation);
  }
}
