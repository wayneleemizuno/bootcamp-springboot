package com.bootcamp.demo.bc_forum.repository;

import com.bootcamp.demo.bc_forum.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {}
