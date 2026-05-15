package com.bootcamp.demo.bc_mtr_station.config;

import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.repository.LineRepository;
import com.bootcamp.demo.bc_mtr_station.repository.LineStationRepository;
import com.bootcamp.demo.bc_mtr_station.repository.StationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStarter implements CommandLineRunner {

  @Autowired private LineRepository lineRepository;
  @Autowired private StationRepository stationRepository;
  @Autowired private LineStationRepository lineStationRepository;

  @Override
  public void run(String... args) {
    this.lineStationRepository.deleteAll();
    this.lineRepository.deleteAll();
    this.stationRepository.deleteAll();

    StationEntity stationHok = StationEntity.builder().code("HOK").name("Hong Kong").build();
    StationEntity stationKow = StationEntity.builder().code("KOW").name("Kowloon").build();
    StationEntity stationTsy = StationEntity.builder().code("TSY").name("Tsing Yi").build();
    StationEntity stationAir = StationEntity.builder().code("AIR").name("Airport").build();
    StationEntity stationAwe = StationEntity.builder().code("AWE").name("AsiaWorld Expo").build();
    StationEntity stationOly = StationEntity.builder().code("OLY").name("Olympic").build();
    StationEntity stationNac = StationEntity.builder().code("NAC").name("Nam Cheong").build();
    StationEntity stationLak = StationEntity.builder().code("LAK").name("Lai King").build();
    StationEntity stationSun = StationEntity.builder().code("SUN").name("Sunny Bay").build();
    StationEntity stationTuc = StationEntity.builder().code("TUC").name("Tung Chung").build();
    StationEntity stationWks = StationEntity.builder().code("WKS").name("Wu Kai Sha").build();
    StationEntity stationMos = StationEntity.builder().code("MOS").name("Ma On Shan").build();
    StationEntity stationHeo = StationEntity.builder().code("HEO").name("Heng On").build();
    StationEntity stationTsh = StationEntity.builder().code("TSH").name("Tai Shui Hang").build();
    StationEntity stationShm = StationEntity.builder().code("SHM").name("Shek Mun").build();
    StationEntity stationCio = StationEntity.builder().code("CIO").name("City One").build();
    StationEntity stationStw = StationEntity.builder().code("STW").name("Sha Tin Wai").build();
    StationEntity stationCkt = StationEntity.builder().code("CKT").name("Che Kung Temple").build();
    StationEntity stationTaw = StationEntity.builder().code("TAW").name("Tai Wai").build();
    StationEntity stationHik = StationEntity.builder().code("HIK").name("Hin Keng").build();
    StationEntity stationDih = StationEntity.builder().code("DIH").name("Diamond Hill").build();
    StationEntity stationKat = StationEntity.builder().code("KAT").name("Kai Tak").build();
    StationEntity stationSuw = StationEntity.builder().code("SUW").name("Sung Wong Toi").build();
    StationEntity stationTkw = StationEntity.builder().code("TKW").name("To Kwa Wan").build();
    StationEntity stationHom = StationEntity.builder().code("HOM").name("Ho Man Tin").build();
    StationEntity stationHuh = StationEntity.builder().code("HUH").name("Hung Hom").build();
    StationEntity stationEts =
        StationEntity.builder().code("ETS").name("East Tsim Sha Tsui").build();
    StationEntity stationAus = StationEntity.builder().code("AUS").name("Austin").build();
    StationEntity stationMef = StationEntity.builder().code("MEF").name("Mei Foo").build();
    StationEntity stationTww = StationEntity.builder().code("TWW").name("Tsuen Wan West").build();
    StationEntity stationKsr = StationEntity.builder().code("KSR").name("Kam Sheung Road").build();
    StationEntity stationYul = StationEntity.builder().code("YUL").name("Yuen Long").build();
    StationEntity stationLop = StationEntity.builder().code("LOP").name("Long Ping").build();
    StationEntity stationTis = StationEntity.builder().code("TIS").name("Tin Shui Wai").build();
    StationEntity stationSih = StationEntity.builder().code("SIH").name("Siu Hong").build();
    StationEntity stationTum = StationEntity.builder().code("TUM").name("Tuen Mun").build();
    StationEntity stationNop = StationEntity.builder().code("NOP").name("North Point").build();
    StationEntity stationQub = StationEntity.builder().code("QUB").name("Quarry Bay").build();
    StationEntity stationYat = StationEntity.builder().code("YAT").name("Yau Tong").build();
    StationEntity stationTik = StationEntity.builder().code("TIK").name("Tiu Keng Leng").build();
    StationEntity stationTko = StationEntity.builder().code("TKO").name("Tseung Kwan O").build();
    StationEntity stationLhp = StationEntity.builder().code("LHP").name("LOHAS Park").build();
    StationEntity stationHah = StationEntity.builder().code("HAH").name("Hang Hau").build();
    StationEntity stationPoa = StationEntity.builder().code("POA").name("Po Lam").build();
    StationEntity stationAdm = StationEntity.builder().code("ADM").name("Admiralty").build();
    StationEntity stationExc =
        StationEntity.builder().code("EXC").name("Exhibition Centre").build();
    StationEntity stationMkk = StationEntity.builder().code("MKK").name("Mong Kok East").build();
    StationEntity stationKot = StationEntity.builder().code("KOT").name("Kowloon Tong").build();
    StationEntity stationSht = StationEntity.builder().code("SHT").name("Sha Tin").build();
    StationEntity stationFot = StationEntity.builder().code("FOT").name("Fo Tan").build();
    StationEntity stationRac = StationEntity.builder().code("RAC").name("Racecourse").build();
    StationEntity stationUni = StationEntity.builder().code("UNI").name("University").build();
    StationEntity stationTap = StationEntity.builder().code("TAP").name("Tai Po Market").build();
    StationEntity stationTwo = StationEntity.builder().code("TWO").name("Tai Wo").build();
    StationEntity stationFan = StationEntity.builder().code("FAN").name("Fanling").build();
    StationEntity stationShs = StationEntity.builder().code("SHS").name("Sheung Shui").build();
    StationEntity stationLow = StationEntity.builder().code("LOW").name("Lo Wu").build();
    StationEntity stationLmc = StationEntity.builder().code("LMC").name("Lok Ma Chau").build();
    StationEntity stationOcp = StationEntity.builder().code("OCP").name("Ocean Park").build();
    StationEntity stationWch = StationEntity.builder().code("WCH").name("Wong Chuk Hang").build();
    StationEntity stationLet = StationEntity.builder().code("LET").name("Lei Tung").build();
    StationEntity stationSoh = StationEntity.builder().code("SOH").name("South Horizons").build();
    StationEntity stationCen = StationEntity.builder().code("CEN").name("Central").build();
    StationEntity stationTst = StationEntity.builder().code("TST").name("Tsim Sha Tsui").build();
    StationEntity stationJor = StationEntity.builder().code("JOR").name("Jordan").build();
    StationEntity stationYmt = StationEntity.builder().code("YMT").name("Yau Ma Tei").build();
    StationEntity stationMok = StationEntity.builder().code("MOK").name("Mong Kok").build();
    StationEntity stationPre = StationEntity.builder().code("PRE").name("Price Edward").build();
    StationEntity stationSsp = StationEntity.builder().code("SSP").name("Sham Shui Po").build();
    StationEntity stationCsw = StationEntity.builder().code("CSW").name("Cheung Sha Wan").build();
    StationEntity stationLck = StationEntity.builder().code("LCK").name("Lai Chi Kok").build();
    StationEntity stationKwf = StationEntity.builder().code("KWF").name("Kwai Fong").build();
    StationEntity stationKwh = StationEntity.builder().code("KWH").name("Kwai Hing").build();
    StationEntity stationTwh = StationEntity.builder().code("TWH").name("Tai Wo Hau").build();
    StationEntity stationTsw = StationEntity.builder().code("TSW").name("Tsuen Wan").build();
    StationEntity stationKet = StationEntity.builder().code("KET").name("Kennedy Town").build();
    StationEntity stationHku = StationEntity.builder().code("HKU").name("HKU").build();
    StationEntity stationSyp = StationEntity.builder().code("SYP").name("Sai Ying Pun").build();
    StationEntity stationShw = StationEntity.builder().code("SHW").name("Sheung Wan").build();
    StationEntity stationWac = StationEntity.builder().code("WAC").name("Wan Chai").build();
    StationEntity stationCab = StationEntity.builder().code("CAB").name("Causeway Bay").build();
    StationEntity stationTih = StationEntity.builder().code("TIH").name("Tin Hau").build();
    StationEntity stationFoh = StationEntity.builder().code("FOH").name("Fortress Hill").build();
    StationEntity stationTak = StationEntity.builder().code("TAK").name("Tai Koo").build();
    StationEntity stationSwh = StationEntity.builder().code("SWH").name("Sai Wan Ho").build();
    StationEntity stationSkw = StationEntity.builder().code("SKW").name("Shau Kei Wan").build();
    StationEntity stationHfc = StationEntity.builder().code("HFC").name("Heng Fa Chuen").build();
    StationEntity stationChw = StationEntity.builder().code("CHW").name("Chai Wan").build();
    StationEntity stationWha = StationEntity.builder().code("WHA").name("Whampoa").build();
    StationEntity stationSkm = StationEntity.builder().code("SKM").name("Shek Kip Mei").build();
    StationEntity stationLof = StationEntity.builder().code("LOF").name("Lok Fu").build();
    StationEntity stationWts = StationEntity.builder().code("WTS").name("Wong Tai Sin").build();
    StationEntity stationChh = StationEntity.builder().code("CHH").name("Choi Hung").build();
    StationEntity stationKob = StationEntity.builder().code("KOB").name("Kowloon Bay").build();
    StationEntity stationNtk = StationEntity.builder().code("NTK").name("Ngau Tau Kok").build();
    StationEntity stationKwt = StationEntity.builder().code("KWT").name("Kwun Tong").build();
    StationEntity stationLat = StationEntity.builder().code("LAT").name("Lam Tin").build();
    StationEntity stationDis =
        StationEntity.builder().code("DIS").name("Disneyland Resort").build();

    LineEntity lineAel = LineEntity.builder().code("AEL").name("Airport Express").build();
    LineEntity lineTcl = LineEntity.builder().code("TCL").name("Tung Chung Line").build();
    LineEntity lineTml = LineEntity.builder().code("TML").name("Tuen Ma Line").build();
    LineEntity lineTkl = LineEntity.builder().code("TKL").name("Tseung Kwan O Line").build();
    LineEntity lineEal = LineEntity.builder().code("EAL").name("East Rail Line").build();
    LineEntity lineSil = LineEntity.builder().code("SIL").name("South Island Line").build();
    LineEntity lineTwl = LineEntity.builder().code("TWL").name("Tsuen Wan Line").build();
    LineEntity lineIsl = LineEntity.builder().code("ISL").name("Island Line").build();
    LineEntity lineKtl = LineEntity.builder().code("KTL").name("Kwun Tong Line").build();
    LineEntity lineDrl = LineEntity.builder().code("DRL").name("Disneyland Resort Line").build();

    stationRepository.saveAll(
        List.of(
            stationHok,
            stationKow,
            stationTsy,
            stationAir,
            stationAwe,
            stationOly,
            stationNac,
            stationLak,
            stationSun,
            stationTuc,
            stationWks,
            stationMos,
            stationHeo,
            stationTsh,
            stationShm,
            stationCio,
            stationStw,
            stationCkt,
            stationTaw,
            stationHik,
            stationDih,
            stationKat,
            stationSuw,
            stationTkw,
            stationHom,
            stationHuh,
            stationEts,
            stationAus,
            stationMef,
            stationTww,
            stationKsr,
            stationYul,
            stationLop,
            stationTis,
            stationSih,
            stationTum,
            stationNop,
            stationQub,
            stationYat,
            stationTik,
            stationTko,
            stationLhp,
            stationHah,
            stationPoa,
            stationAdm,
            stationExc,
            stationMkk,
            stationKot,
            stationSht,
            stationFot,
            stationRac,
            stationUni,
            stationTap,
            stationTwo,
            stationFan,
            stationShs,
            stationLow,
            stationLmc,
            stationOcp,
            stationWch,
            stationLet,
            stationSoh,
            stationCen,
            stationTst,
            stationJor,
            stationYmt,
            stationMok,
            stationPre,
            stationSsp,
            stationCsw,
            stationLck,
            stationKwf,
            stationKwh,
            stationTwh,
            stationTsw,
            stationKet,
            stationHku,
            stationSyp,
            stationShw,
            stationWac,
            stationCab,
            stationTih,
            stationFoh,
            stationTak,
            stationSwh,
            stationSkw,
            stationHfc,
            stationChw,
            stationWha,
            stationSkm,
            stationLof,
            stationWts,
            stationChh,
            stationKob,
            stationNtk,
            stationKwt,
            stationLat,
            stationDis));

    lineRepository.saveAll(
        List.of(
            lineAel, lineTcl, lineTml, lineTkl, lineEal, lineSil, lineTwl, lineIsl, lineKtl,
            lineDrl));

    lineStationRepository.saveAll(
        List.of(
            LineStationEntity.builder().lineEntity(lineAel).stationEntity(stationHok).build(),
            LineStationEntity.builder().lineEntity(lineAel).stationEntity(stationKow).build(),
            LineStationEntity.builder().lineEntity(lineAel).stationEntity(stationTsy).build(),
            LineStationEntity.builder().lineEntity(lineAel).stationEntity(stationAir).build(),
            LineStationEntity.builder().lineEntity(lineAel).stationEntity(stationAwe).build(),
            LineStationEntity.builder().lineEntity(lineTcl).stationEntity(stationHok).build(),
            LineStationEntity.builder().lineEntity(lineTcl).stationEntity(stationKow).build(),
            LineStationEntity.builder().lineEntity(lineTcl).stationEntity(stationOly).build(),
            LineStationEntity.builder().lineEntity(lineTcl).stationEntity(stationNac).build(),
            LineStationEntity.builder().lineEntity(lineTcl).stationEntity(stationLak).build(),
            LineStationEntity.builder().lineEntity(lineTcl).stationEntity(stationTsy).build(),
            LineStationEntity.builder().lineEntity(lineTcl).stationEntity(stationSun).build(),
            LineStationEntity.builder().lineEntity(lineTcl).stationEntity(stationTuc).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationWks).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationMos).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationHeo).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationTsh).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationShm).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationCio).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationStw).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationCkt).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationTaw).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationHik).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationDih).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationKat).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationSuw).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationTkw).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationHom).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationHuh).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationEts).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationAus).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationNac).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationMef).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationTww).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationKsr).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationYul).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationLop).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationTis).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationSih).build(),
            LineStationEntity.builder().lineEntity(lineTml).stationEntity(stationTum).build(),
            LineStationEntity.builder().lineEntity(lineTkl).stationEntity(stationNop).build(),
            LineStationEntity.builder().lineEntity(lineTkl).stationEntity(stationQub).build(),
            LineStationEntity.builder().lineEntity(lineTkl).stationEntity(stationYat).build(),
            LineStationEntity.builder().lineEntity(lineTkl).stationEntity(stationTik).build(),
            LineStationEntity.builder().lineEntity(lineTkl).stationEntity(stationTko).build(),
            LineStationEntity.builder().lineEntity(lineTkl).stationEntity(stationLhp).build(),
            LineStationEntity.builder().lineEntity(lineTkl).stationEntity(stationHah).build(),
            LineStationEntity.builder().lineEntity(lineTkl).stationEntity(stationPoa).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationAdm).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationExc).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationHuh).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationMkk).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationKot).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationTaw).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationSht).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationFot).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationRac).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationUni).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationTap).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationTwo).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationFan).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationShs).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationLow).build(),
            LineStationEntity.builder().lineEntity(lineEal).stationEntity(stationLmc).build(),
            LineStationEntity.builder().lineEntity(lineSil).stationEntity(stationAdm).build(),
            LineStationEntity.builder().lineEntity(lineSil).stationEntity(stationOcp).build(),
            LineStationEntity.builder().lineEntity(lineSil).stationEntity(stationWch).build(),
            LineStationEntity.builder().lineEntity(lineSil).stationEntity(stationLet).build(),
            LineStationEntity.builder().lineEntity(lineSil).stationEntity(stationSoh).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationCen).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationAdm).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationTst).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationJor).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationYmt).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationMok).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationPre).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationSsp).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationCsw).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationLck).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationMef).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationLak).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationKwf).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationKwh).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationTwh).build(),
            LineStationEntity.builder().lineEntity(lineTwl).stationEntity(stationTsw).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationKet).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationHku).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationSyp).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationShw).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationCen).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationAdm).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationWac).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationCab).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationTih).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationFoh).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationNop).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationQub).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationTak).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationSwh).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationSkw).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationHfc).build(),
            LineStationEntity.builder().lineEntity(lineIsl).stationEntity(stationChw).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationWha).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationHom).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationYmt).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationMok).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationPre).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationSkm).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationKot).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationLof).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationWts).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationDih).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationChh).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationKob).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationNtk).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationKwt).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationLat).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationYat).build(),
            LineStationEntity.builder().lineEntity(lineKtl).stationEntity(stationTik).build(),
            LineStationEntity.builder().lineEntity(lineDrl).stationEntity(stationSun).build(),
            LineStationEntity.builder().lineEntity(lineDrl).stationEntity(stationDis).build()));
  }
}
