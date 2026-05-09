package com.bootcamp.demo.bc_forum.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorTypes {
  USER_NOT_FOUND("1", "User not found."),
  INVALID_ID("2", "Invalid ID"),
  RT_ERROR("3", "RestTemplate Error - JsonPlaceHolder.");

  private String code;
  private String message;
}
