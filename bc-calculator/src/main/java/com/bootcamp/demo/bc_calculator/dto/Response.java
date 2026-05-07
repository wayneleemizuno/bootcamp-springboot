package com.bootcamp.demo.bc_calculator.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Response implements CalculationResult {
  private String x;
  private String y;
  private String operation;
  private String result;
}
