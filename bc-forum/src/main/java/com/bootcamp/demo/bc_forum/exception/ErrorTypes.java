package com.bootcamp.demo.bc_forum.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorTypes {
  TARGET_NOT_FOUND("1", "target not found."),
  INVALID_ID("2", "Invalid ID");

  private String code;
  private String message;
}
