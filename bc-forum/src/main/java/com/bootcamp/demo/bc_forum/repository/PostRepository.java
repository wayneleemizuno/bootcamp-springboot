package com.bootcamp.demo.bc_forum.repository;

import com.bootcamp.demo.bc_forum.entity.PostEntity;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
  List<PostEntity> findAllByUserEntity(UserEntity userEntity);

  Optional<PostEntity> findByForumPostId(Long forumPostId);

  void deleteByForumPostId(Long forumPostId);
}
