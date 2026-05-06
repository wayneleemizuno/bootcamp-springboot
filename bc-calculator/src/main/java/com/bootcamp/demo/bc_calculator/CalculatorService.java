package com.bootcamp.demo.bc_calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {


  public Double calculate(String x, String y, String operation) {
    Double result = 0.0;
    try {
      Double numX = Double.parseDouble(x.trim());
      Double numY = Double.parseDouble(y.trim());
      switch (operation) {
        case "add":
          result = BigDecimal.valueOf(numX).add(BigDecimal.valueOf(numY)).doubleValue();
          break;
        case "sub":
          result = BigDecimal.valueOf(numX).subtract(BigDecimal.valueOf(numY)).doubleValue();
          break;
        case "mul":
          result = BigDecimal.valueOf(numX).multiply(BigDecimal.valueOf(numY)).doubleValue();
          break;
        case "div":
          result = BigDecimal.valueOf(numX)
              .divide(BigDecimal.valueOf(numY), 5, RoundingMode.HALF_UP).doubleValue();
          break;
        default:
          throw BusinessException.of(ResultType.INVALID_OPERATION);
      }
    } catch (NumberFormatException e) {
      throw BusinessException.of(ResultType.INVALID_VALUE);
    }
    return result;
  }



}
