package com.bootcamp.demo.demo_call_api.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
// ! Serialisation (object -> JSON)
public class ForumUserResp { // Resp = Response
  private String email;
  private String phone;
  private String username;
}
