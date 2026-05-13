package com.bootcamp.demo.bc_mtr_station;

import com.bootcamp.demo.bc_mtr_station.repository.LineRepository;
import com.bootcamp.demo.bc_mtr_station.repository.LineStationRepository;
import com.bootcamp.demo.bc_mtr_station.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BcMtrStationApplication {
  @Autowired StationRepository stationRepository;
  @Autowired LineRepository lineRepository;
  @Autowired LineStationRepository lineStationRepository;

  public static void main(String[] args) {
    SpringApplication.run(BcMtrStationApplication.class, args);
  }
}
