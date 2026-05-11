package com.bootcamp.demo.demo_call_api.service;

import com.bootcamp.demo.demo_call_api.dto.ForumUserResp;
import com.bootcamp.demo.demo_call_api.model.UserDto;
import java.util.List;

public interface ForumService {
  List<UserDto> getUsers();

  List<ForumUserResp> postUsers();

  List<ForumUserResp> getDbUsers();
}
