package com.bootcamp.demo.bc_forum.repository;

import com.bootcamp.demo.bc_forum.entity.AddressEntity;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
  Optional<AddressEntity> findByUserEntity(UserEntity userEntity);
}
