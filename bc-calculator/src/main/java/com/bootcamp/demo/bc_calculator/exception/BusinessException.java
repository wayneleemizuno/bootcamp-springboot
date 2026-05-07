package com.bootcamp.demo.bc_calculator.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
  private String code;
  private String x;
  private String y;
  private String operation;

  public static BusinessException of(ErrorType resultType, String x, String y, String op) {
    return new BusinessException(resultType, x, y, op);
  }

  private BusinessException(ErrorType resultType, String x, String y, String op) {
    super(resultType.getMessage());
    this.code = resultType.getCode();
    this.x = x;
    this.y = y;
    this.operation = op;
  }
}
