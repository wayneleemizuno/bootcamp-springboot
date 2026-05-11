package com.bootcamp.demo.bc_forum.repository;

import com.bootcamp.demo.bc_forum.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {}
