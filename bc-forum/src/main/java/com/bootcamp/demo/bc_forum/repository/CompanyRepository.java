package com.bootcamp.demo.bc_forum.repository;

import com.bootcamp.demo.bc_forum.entity.CompanyEntity;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
  Optional<CompanyEntity> findByUserEntity(UserEntity userEntity);
}
