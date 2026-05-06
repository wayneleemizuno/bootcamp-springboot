package com.bootcamp.demo.demo_user_login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.demo.demo_user_login.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  // findAll, findById, save, saveAll, deleteById
  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findByUsernameAndPassword(String username, String password);
}
