package com.bootcamp.demo.bc_mtr_station.controller;

import com.bootcamp.demo.bc_mtr_station.dto.LineSignalDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface LineOperation {
  @GetMapping(value = "/lines")
  List<LineEntity> getAllLines();

  @GetMapping(value = "/signal")
  LineSignalDto getLineSignal(@RequestParam String lineCode);

  @GetMapping(value = "/signals")
  List<LineSignalDto> getAllLineSignals();
}
