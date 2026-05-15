package com.bootcamp.demo.bc_mtr_station.dto;

import com.bootcamp.demo.bc_mtr_station.model.Signal;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LineSignalDto {
  @JsonProperty("line")
  private String lineCode;

  private Signal signal;

  @JsonProperty("delay_stations")
  private List<String> delayStations;

  @JsonProperty("curr_time")
  private String currentTime;

  @JsonProperty("sys_time")
  private String systemTime;
}
