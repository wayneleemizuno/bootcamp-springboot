package com.bootcamp.demo.bc_forum.controller;

import com.bootcamp.demo.bc_forum.dto.UserCommentDto;
import com.bootcamp.demo.bc_forum.dto.UserDetailDto;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

public interface ForumOperation {
  List<UserDetailDto> getUserDetails();

  UserCommentDto getUserComments(@RequestParam Long id);

  List<UserEntity> saveAllData();
}
