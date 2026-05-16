package com.bootcamp.demo.bc_mtr_station.model;

import java.util.List;
import lombok.Getter;

@Getter
public enum MtrStation {
  // Airport Express / Tung Chung Line
  HOK("Hong Kong", MtrLine.AEL, MtrLine.TCL),
  KOW("Kowloon", MtrLine.AEL, MtrLine.TCL),
  TSY("Tsing Yi", MtrLine.AEL, MtrLine.TCL),
  AIR("Airport", MtrLine.AEL),
  AWE("AsiaWorld Expo", MtrLine.AEL),
  OLY("Olympic", MtrLine.TCL),
  Nac("Nam Cheong", MtrLine.TCL, MtrLine.TML), // Shared station
  LAK("Lai King", MtrLine.TCL, MtrLine.TWL),
  SUN("Sunny Bay", MtrLine.TCL, MtrLine.DRL),
  TUC("Tung Chung", MtrLine.TCL),

  // Tuen Ma Line
  WKS("Wu Kai Sha", MtrLine.TML),
  MOS("Ma On Shan", MtrLine.TML),
  HEO("Heng On", MtrLine.TML),
  TSH("Tai Shui Hang", MtrLine.TML),
  SHM("Shek Mun", MtrLine.TML),
  CIO("City One", MtrLine.TML),
  STW("Sha Tin Wai", MtrLine.TML),
  CKT("Che Kung Temple", MtrLine.TML),
  TAW("Tai Wai", MtrLine.TML, MtrLine.EAL),
  HIK("Hin Keng", MtrLine.TML),
  DIH("Diamond Hill", MtrLine.TML, MtrLine.KTL),
  KAT("Kai Tak", MtrLine.TML),
  SUW("Sung Wong Toi", MtrLine.TML),
  TKW("To Kwa Wan", MtrLine.TML),
  HOM("Ho Man Tin", MtrLine.TML, MtrLine.KTL),
  HUH("Hung Hom", MtrLine.TML, MtrLine.EAL),
  ETS("East Tsim Sha Tsui", MtrLine.TML),
  AUS("Austin", MtrLine.TML),
  MEF("Mei Foo", MtrLine.TML, MtrLine.TWL),
  TWW("Tsuen Wan West", MtrLine.TML),
  KSR("Kam Sheung Road", MtrLine.TML),
  YUL("Yuen Long", MtrLine.TML),
  LOP("Long Ping", MtrLine.TML),
  TIS("Tin Shui Wai", MtrLine.TML),
  SIH("Siu Hong", MtrLine.TML),
  TUM("Tuen Mun", MtrLine.TML),

  // Tseung Kwan O Line
  NOP("North Point", MtrLine.TKL, MtrLine.ISL),
  QUB("Quarry Bay", MtrLine.TKL, MtrLine.ISL),
  YAT("Yau Tong", MtrLine.TKL, MtrLine.KTL),
  TIK("Tiu Keng Leng", MtrLine.TKL, MtrLine.KTL),
  TKO("Tseung Kwan O", MtrLine.TKL),
  LHP("LOHAS Park", MtrLine.TKL),
  HAH("Hang Hau", MtrLine.TKL),
  POA("Po Lam", MtrLine.TKL),

  // East Rail Line
  ADM("Admiralty", MtrLine.EAL, MtrLine.SIL, MtrLine.TWL, MtrLine.ISL), // Mega interchange
  EXC("Exhibition Centre", MtrLine.EAL),
  MKK("Mong Kok East", MtrLine.EAL),
  KOT("Kowloon Tong", MtrLine.EAL, MtrLine.KTL),
  SHT("Sha Tin", MtrLine.EAL),
  FOT("Fo Tan", MtrLine.EAL),
  RAC("Racecourse", MtrLine.EAL),
  UNI("University", MtrLine.EAL),
  TAP("Tai Po Market", MtrLine.EAL),
  TWO("Tai Wo", MtrLine.EAL),
  FAN("Fanling", MtrLine.EAL),
  SHS("Sheung Shui", MtrLine.EAL),
  LOW("Lo Wu", MtrLine.EAL),
  LMC("Lok Ma Chau", MtrLine.EAL),

  // South Island Line
  OCP("Ocean Park", MtrLine.SIL),
  WCH("Wong Chuk Hang", MtrLine.SIL),
  LET("Lei Tung", MtrLine.SIL),
  SOH("South Horizons", MtrLine.SIL),

  // Tsuen Wan Line & Island Line Extra
  CEN("Central", MtrLine.TWL, MtrLine.ISL),
  TST("Tsim Sha Tsui", MtrLine.TWL),
  JOR("Jordan", MtrLine.TWL),
  YMT("Yau Ma Tei", MtrLine.TWL, MtrLine.KTL),
  MOK("Mong Kok", MtrLine.TWL, MtrLine.KTL),
  PRE("Prince Edward", MtrLine.TWL, MtrLine.KTL),
  SSP("Sham Shui Po", MtrLine.TWL),
  CSW("Cheung Sha Wan", MtrLine.TWL),
  LCK("Lai Chi Kok", MtrLine.TWL),
  KWF("Kwai Fong", MtrLine.TWL),
  KWH("Kwai Hing", MtrLine.TWL),
  TWH("Tai Wo Hau", MtrLine.TWL),
  TSW("Tsuen Wan", MtrLine.TWL),
  KET("Kennedy Town", MtrLine.ISL),
  HKU("HKU", MtrLine.ISL),
  SYP("Sai Ying Pun", MtrLine.ISL),
  SHW("Sheung Wan", MtrLine.ISL),
  WAC("Wan Chai", MtrLine.ISL),
  CAB("Causeway Bay", MtrLine.ISL),
  TIH("Tin Hau", MtrLine.ISL),
  FOH("Fortress Hill", MtrLine.ISL),
  TAK("Tai Koo", MtrLine.ISL),
  SWH("Sai Wan Ho", MtrLine.ISL),
  SKW("Shau Kei Wan", MtrLine.ISL),
  HFC("Heng Fa Chuen", MtrLine.ISL),
  CHW("Chai Wan", MtrLine.ISL),

  // Kwun Tong Line Extra
  WHA("Whampoa", MtrLine.KTL),
  SKM("Shek Kip Mei", MtrLine.KTL),
  LOF("Lok Fu", MtrLine.KTL),
  WTS("Wong Tai Sin", MtrLine.KTL),
  CHH("Choi Hung", MtrLine.KTL),
  KOB("Kowloon Bay", MtrLine.KTL),
  NTK("Ngau Tau Kok", MtrLine.KTL),
  KWT("Kwun Tong", MtrLine.KTL),
  LAT("Lam Tin", MtrLine.KTL),

  // Disneyland Resort Line Extra
  DIS("Disneyland Resort", MtrLine.DRL);

  private final String name;
  private final List<MtrLine> lines;

  MtrStation(String name, MtrLine... lines) {
    this.name = name;
    this.lines = List.of(lines);
  }
}
