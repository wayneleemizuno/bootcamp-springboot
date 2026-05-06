package com.bootcamp.demo.bc_calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CalculatorInput {
  private String x;
  private String y;
  private String operation;
}
