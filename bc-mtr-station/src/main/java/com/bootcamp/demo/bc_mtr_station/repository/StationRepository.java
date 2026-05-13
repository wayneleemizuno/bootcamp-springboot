package com.bootcamp.demo.bc_mtr_station.repository;

import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<StationEntity, Long> {}
