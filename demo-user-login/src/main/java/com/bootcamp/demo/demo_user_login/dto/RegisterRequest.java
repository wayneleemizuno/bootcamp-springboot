package com.bootcamp.demo.demo_user_login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class RegisterRequest {
  private String username;
  private String password;
  private String email;
}
