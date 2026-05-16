package com.bootcamp.demo.bc_mtr_station.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/db")
public interface DbInitOperation {

  @PostMapping("/init")
  ResponseEntity<String> initializeDatabase();
}
