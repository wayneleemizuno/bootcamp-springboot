package com.bootcamp.demo.bc_forum.repository;

import com.bootcamp.demo.bc_forum.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findById(Long id);

  Optional<UserEntity> findByForumUserId(Long forumUserId);
}
