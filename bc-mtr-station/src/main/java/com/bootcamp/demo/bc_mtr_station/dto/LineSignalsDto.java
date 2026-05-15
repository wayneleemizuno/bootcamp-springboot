package com.bootcamp.demo.bc_mtr_station.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LineSignalsDto {
  List<LineSignalDto> linesignals;
}
