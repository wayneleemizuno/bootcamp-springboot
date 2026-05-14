package com.bootcamp.demo.bc_mtr_station.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TrainDto {
  private String stationCode;
  private Integer ttnt;
  private Integer plat;
  private LocalDateTime time;
  private String dest;
}
