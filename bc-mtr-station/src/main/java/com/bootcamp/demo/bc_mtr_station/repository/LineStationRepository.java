package com.bootcamp.demo.bc_mtr_station.repository;

import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineStationRepository extends JpaRepository<LineStationEntity, Long> {}
