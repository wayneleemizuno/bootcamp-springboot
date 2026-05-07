package com.bootcamp.demo.demo_call_api.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bootcamp.demo.demo_call_api.dto.ForumUserDto;
import com.bootcamp.demo.demo_call_api.entity.UserEntity;
import com.bootcamp.demo.demo_call_api.service.ForumService;

@Controller
@ResponseBody
public class ForumController {
  @Autowired
  ForumService forumService;

  @GetMapping(value = "/users")
  public List<ForumUserDto> getAllUsers() {
    return this.forumService.getUsers().stream().map(user -> {
      return ForumUserDto.builder()//
          .email(user.getEmail())//
          .phone(user.getPhone())//
          .username(user.getUsername())//
          .build();
    }).collect(Collectors.toList());
  }

  @PostMapping(value = "/users")
  public List<UserEntity> postToDb() {
    return this.forumService.postUsers();
  }

  @GetMapping(value = "/db_users")
  public List<UserEntity> getDbUsers() {
    return this.forumService.getDbUsers();
  }

}
