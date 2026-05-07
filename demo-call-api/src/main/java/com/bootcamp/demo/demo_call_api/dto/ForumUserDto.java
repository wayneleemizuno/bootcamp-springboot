package com.bootcamp.demo.demo_call_api.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ForumUserDto {
  private String email;
  private String phone;
  private String username;
}
