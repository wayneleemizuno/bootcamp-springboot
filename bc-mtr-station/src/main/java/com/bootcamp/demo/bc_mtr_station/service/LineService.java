package com.bootcamp.demo.bc_mtr_station.service;

import com.bootcamp.demo.bc_mtr_station.dto.LineSignalDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import java.util.List;

public interface LineService {
  LineEntity createLine(String code, String name);

  List<LineEntity> getAllLines();

  LineSignalDto getLineSignal(String lineCode);
}
