package com.bootcamp.demo.bc_calculator;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseStatus implements CalculationResult {
  private String x;
  private String y;
  private String operation;
  private String result;
}
