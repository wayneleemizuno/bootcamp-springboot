package com.bootcamp.demo.demo_call_api.controller;

import com.bootcamp.demo.demo_call_api.dto.ForumUserDto;
import com.bootcamp.demo.demo_call_api.mapper.DtoMapper;
import com.bootcamp.demo.demo_call_api.service.ForumService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class ForumController {
  @Autowired ForumService forumService;
  @Autowired DtoMapper dtoMapper;

  @GetMapping(value = "/users")
  public List<ForumUserDto> getAllUsers() {
    return this.forumService.getUsers().stream()
        .map(userDto -> this.dtoMapper.map(userDto))
        .collect(Collectors.toList());
  }

  @PostMapping(value = "/users")
  public List<ForumUserDto> postToDb() {
    return this.forumService.postUsers();
  }

  @GetMapping(value = "/db_users")
  public List<ForumUserDto> getDbUsers() {
    return this.forumService.getDbUsers();
  }
}
