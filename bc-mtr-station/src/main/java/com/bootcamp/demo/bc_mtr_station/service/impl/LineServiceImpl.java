package com.bootcamp.demo.bc_mtr_station.service.impl;

import com.bootcamp.demo.bc_mtr_station.dto.LineSignalDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.repository.LineRepository;
import com.bootcamp.demo.bc_mtr_station.repository.LineStationRepository;
import com.bootcamp.demo.bc_mtr_station.service.LineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineServiceImpl implements LineService {
  @Autowired LineRepository lineRepository;

  @Autowired LineStationRepository lineStationRepository;

  @Override
  public List<LineEntity> getAllLines() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllLines'");
  }

  @Override
  public LineSignalDto getLineSignal(String lineCode) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getLineSignal'");
  }
}
