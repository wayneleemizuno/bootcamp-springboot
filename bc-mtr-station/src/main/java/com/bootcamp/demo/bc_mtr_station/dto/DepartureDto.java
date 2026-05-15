package com.bootcamp.demo.bc_mtr_station.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DepartureDto {
  @JsonProperty("curr_time")
  private String currentTime;

  @JsonProperty("sys_time")
  private String systemTime;

  @JsonProperty("current_station")
  private String currentStation;

  private List<Train> trains;

  @Builder
  @Getter
  public static class Train {
    private String destination;

    @JsonProperty("arrival_time")
    private String arrivalTime;

    private String direction;
  }
}
