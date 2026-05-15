package com.bootcamp.demo.bc_mtr_station.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public class TrainTimeDTO {

  private Integer status;
  private String message;
  private String isdelay;

  @JsonProperty("sys_time")
  private String sysTime;

  @JsonProperty("curr_time")
  private String currTime;

  private Map<String, StationSchedule> data;

  @Getter
  public static class StationSchedule {

    @JsonProperty("sys_time")
    private String sysTime;

    @JsonProperty("curr_time")
    private String currTime;

    // @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("UP")
    private List<DepartureDetails> upDepartures;

    // @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("DOWN")
    private List<DepartureDetails> downDepartures;
  }

  @Getter
  public static class DepartureDetails {
    private Integer ttnt; // Time to next train (minutes)
    private String valid; // Data validity
    private Integer plat; // Platform number

    private String time; // Arrival time

    private String source;
    private String dest; // Destination station code
    private String seq; // Sequence number
  }
}
