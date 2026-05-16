package com.bootcamp.demo.bc_mtr_station.config;

import com.bootcamp.demo.bc_mtr_station.service.DbInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStarter implements CommandLineRunner {

  @Autowired private DbInitService dbInitService;

  @Override
  public void run(String... args) {
    // this.dbInitService.resetAndSeedDatabase();
  }
}
