package com.bootcamp.demo.bc_forum.controller.impl;

import com.bootcamp.demo.bc_forum.controller.ForumOperation;
import com.bootcamp.demo.bc_forum.dto.UserCommentDto;
import com.bootcamp.demo.bc_forum.dto.UserDetailDto;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import com.bootcamp.demo.bc_forum.service.impl.ForumServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForumController implements ForumOperation {
  @Autowired ForumServiceImpl forumServiceImpl;

  @GetMapping(value = "/userdetail")
  public List<UserDetailDto> getUserDetails() {
    return this.forumServiceImpl.getUserDetails();
  }

  @GetMapping(value = "/usercomments")
  public UserCommentDto getUserComments(@RequestParam Long id) {
    return this.forumServiceImpl.getUserComments(id);
  }

  @PostMapping(value = "/all_data")
  public List<UserEntity> saveAllData() {
    return this.forumServiceImpl.saveAllData();
  }
}
