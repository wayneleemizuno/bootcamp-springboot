package com.bootcamp.demo.bc_mtr_station.controller.impl;

import com.bootcamp.demo.bc_mtr_station.controller.LineOperation;
import com.bootcamp.demo.bc_mtr_station.dto.LineSignalDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.service.LineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineController implements LineOperation {
  @Autowired LineService lineService;

  @Override
  public List<LineEntity> getAllLines() {
    return this.lineService.getAllLines();
  }

  @Override
  public LineSignalDto getLineSignal(String lineCode) {
    return this.lineService.getLineSignal(lineCode);
  }

  @Override
  public List<LineSignalDto> getAllLineSignals() {
    return this.lineService.getAllLineSignals();
  }
}
