package com.bootcamp.demo.bc_mtr_station.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class StationResDto {
  private String code;
  private String name;
}
