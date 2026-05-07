package com.bootcamp.demo.bc_calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.demo.bc_calculator.entity.RecordEntity;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {

}
