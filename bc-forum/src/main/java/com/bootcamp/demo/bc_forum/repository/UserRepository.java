package com.bootcamp.demo.bc_forum.repository;

import com.bootcamp.demo.bc_forum.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {}
