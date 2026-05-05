package com.bootcamp.demo.demo_jpa_database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.demo.demo_jpa_database.entity.OrderEntity;

// Long -> PK
// OrderEntity -> Table
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
  // * Hibernate Framework
  // create a java class, which implements this interface
  // it automatically creates SQL syntax for the corresponding database
  // # insert (Java Method: save Entity)
  // # update (Java Method: save Entity)
  // # delete (Java Method: deleteByXXX)
  // # select (Java Method: findByXXX)
  // Dependency (pom) decides which database to connect
}
