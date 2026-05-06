package com.bootcamp.demo.demo_user_login.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BusinessException extends Exception {
  private SystemError sysErr;
  private String message;
}
