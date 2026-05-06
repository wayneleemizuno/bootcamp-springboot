package com.bootcamp.demo.demo_user_login.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterResponse {
  private Boolean isSuccessful;
  private String message;
}
