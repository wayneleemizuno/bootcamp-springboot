package com.bootcamp.demo.bc_mtr_station.model;

import lombok.Getter;

@Getter
public enum MtrLine {
  AEL("Airport Express"),
  TCL("Tung Chung Line"),
  TML("Tuen Ma Line"),
  TKL("Tseung Kwan O Line"),
  EAL("East Rail Line"),
  SIL("South Island Line"),
  TWL("Tsuen Wan Line"),
  ISL("Island Line"),
  KTL("Kwun Tong Line"),
  DRL("Disneyland Resort Line");

  private final String name;

  MtrLine(String name) {
    this.name = name;
  }
}
