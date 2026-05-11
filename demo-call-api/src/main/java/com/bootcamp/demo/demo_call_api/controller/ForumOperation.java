package com.bootcamp.demo.demo_call_api.controller;

import com.bootcamp.demo.demo_call_api.dto.ForumUserResp;
import java.util.List;

public interface ForumOperation {
  List<ForumUserResp> getAllUsers();

  List<ForumUserResp> postToDb();

  List<ForumUserResp> getDbUsers();
}
