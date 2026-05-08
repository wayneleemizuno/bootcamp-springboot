package com.bootcamp.demo.demo_call_api.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DbUserDto {
  private Long user_id;
  private String user_name;
  private String name;
  private String phone;
  private String email;
}
