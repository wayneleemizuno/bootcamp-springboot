package com.bootcamp.demo.bc_mtr_station.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public class TrainTimeDTO {

  private Integer status;
  private String message;
  private String isdelay;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonProperty("sys_time")
  private LocalDateTime sysTime;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonProperty("curr_time")
  private LocalDateTime currTime;

  private Map<String, StationSchedule> data;

  @Getter
  public static class StationSchedule {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("sys_time")
    private LocalDateTime sysTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("curr_time")
    private LocalDateTime currTime;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("UP")
    private List<DepartureDetails> upDepartures;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("DOWN")
    private List<DepartureDetails> downDepartures;
  }

  @Getter
  public static class DepartureDetails {
    private Integer ttnt; // Time to next train (minutes)
    private String valid; // Data validity
    private Integer plat; // Platform number

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time; // Arrival time

    private String source;
    private String dest; // Destination station code
    private String seq; // Sequence number
  }
}
