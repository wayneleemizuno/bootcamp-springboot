package com.bootcamp.demo.demo_user_login.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponse {
  private Boolean isSuccessful;
  private String message;
}
