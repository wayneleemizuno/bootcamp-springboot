package com.bootcamp.demo.demo_call_api.repository;

import com.bootcamp.demo.demo_call_api.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// After Springboot 3.x -> @Repository is optional
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  // * 1. JPA Method
  // Return List<UserEntity> or Optional<UserEntity>
  List<UserEntity> findByPhone(String phone);
  // findById or findAll
  // deleteById or deleteAll
  // save(Entity)
  // saveAll (List of Entity)

  // * 2. JPQL (Java SQL)
  // * 3. Native SQL (product specific)

}
