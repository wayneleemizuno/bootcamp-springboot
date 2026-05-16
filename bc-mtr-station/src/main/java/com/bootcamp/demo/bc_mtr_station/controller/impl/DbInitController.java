package com.bootcamp.demo.bc_mtr_station.controller.impl;

import com.bootcamp.demo.bc_mtr_station.controller.DbInitOperation;
import com.bootcamp.demo.bc_mtr_station.service.DbInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DbInitController implements DbInitOperation {

  @Autowired private DbInitService dbInitService;

  @Override
  public ResponseEntity<String> initializeDatabase() {
    this.dbInitService.resetAndSeedDatabase();

    return ResponseEntity.ok(
        "Database wiped and re-seeded successfully from Enums. Cache refreshed.");
  }
}
