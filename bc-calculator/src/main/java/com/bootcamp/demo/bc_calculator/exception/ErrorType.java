package com.bootcamp.demo.bc_calculator.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorType {
  INVALID_VALUE("001", "The value must be a number!"), INVALID_OPERATION("002",
      "Invalid operation!");

  private String code;
  private String message;
}
