package com.bootcamp.demo.bc_mtr_station.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DepartureDto {
  @JsonProperty("curr_time")
  private LocalDateTime currentTime;

  @JsonProperty("sys_time")
  private LocalDateTime systemTime;

  @JsonProperty("current_station")
  private String currentStation;

  private List<Train> trains;

  @Builder
  @Getter
  public static class Train {
    private String destination;

    @JsonProperty("arrival_time")
    private LocalDateTime arrivalTime;

    private String direction;
  }
}
