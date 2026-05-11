package com.bootcamp.demo.bc_forum.repository;

import com.bootcamp.demo.bc_forum.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {}
