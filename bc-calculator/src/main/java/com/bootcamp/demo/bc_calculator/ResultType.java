package com.bootcamp.demo.bc_calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultType {
  SUCCESS("001", "Calculation Done."), INVALID_VALUE("002",
      "The value must be a number!"), INVALID_OPERATION("003", "Invalid operation!");

  private String code;
  private String message;
}
