package com.bootcamp.demo.bc_mtr_station.repository;

import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineStationRepository extends JpaRepository<LineStationEntity, Long> {
  List<LineStationEntity> findByStationEntity(StationEntity stationEntity);

  void deleteByStationEntity(StationEntity stationEntity);
}
