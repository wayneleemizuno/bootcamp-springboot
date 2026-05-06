package com.bootcamp.demo.bc_calculator;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  private String code;

  public static BusinessException of(ResultType resultType) {
    return new BusinessException(resultType);
  }

  private BusinessException(ResultType resultType) {
    super(resultType.getMessage());
    this.code = resultType.getCode();
  }

}
