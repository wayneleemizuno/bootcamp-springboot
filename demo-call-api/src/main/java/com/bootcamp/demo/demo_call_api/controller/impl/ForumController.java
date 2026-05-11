package com.bootcamp.demo.demo_call_api.controller.impl;

import com.bootcamp.demo.demo_call_api.controller.ForumOperation;
import com.bootcamp.demo.demo_call_api.dto.ForumUserResp;
import com.bootcamp.demo.demo_call_api.mapper.DtoMapper;
import com.bootcamp.demo.demo_call_api.service.impl.ForumServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// ! Spring Context -> store all the Beans
// ! Bean (stateless object)
@RestController
public class ForumController implements ForumOperation {
  // ! @Autowire -> inject a bean from context
  // default: injection must be successful
  @Autowired ForumServiceImpl forumService;
  @Autowired DtoMapper dtoMapper;

  @GetMapping(value = "/users")
  public List<ForumUserResp> getAllUsers() {
    return this.forumService.getUsers().stream()
        .map(userDto -> this.dtoMapper.map(userDto))
        .collect(Collectors.toList());
  }

  @PostMapping(value = "/users")
  public List<ForumUserResp> postToDb() {
    return this.forumService.postUsers();
  }

  @GetMapping(value = "/db_users")
  public List<ForumUserResp> getDbUsers() {
    return this.forumService.getDbUsers();
  }
}
