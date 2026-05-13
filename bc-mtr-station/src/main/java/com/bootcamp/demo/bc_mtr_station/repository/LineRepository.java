package com.bootcamp.demo.bc_mtr_station.repository;

import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<LineEntity, Long> {}
