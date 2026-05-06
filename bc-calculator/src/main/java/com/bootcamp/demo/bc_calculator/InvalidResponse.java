package com.bootcamp.demo.bc_calculator;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InvalidResponse implements CalculationResult {
  private String code;
  private String message;
}
