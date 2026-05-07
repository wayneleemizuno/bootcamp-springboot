package com.bootcamp.demo.bc_calculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.bootcamp.demo.bc_calculator.dto.ResultType;
import com.bootcamp.demo.bc_calculator.exception.BusinessException;

@Service
public class CalculatorService {

  public BigDecimal calculate(String x, String y, String operation) {
    BigDecimal result = BigDecimal.ZERO;
    String operationType = operation.trim();
    try {
      BigDecimal numX = new BigDecimal(x.trim().replaceAll(" ", ""));
      BigDecimal numY = new BigDecimal(y.trim().replaceAll(" ", ""));
      switch (operationType) {
        case "add":
          result = numX.add(numY).setScale(5, RoundingMode.HALF_UP);
          break;
        case "sub":
          result = numX.subtract(numY).setScale(5, RoundingMode.HALF_UP);
          break;
        case "mul":
          result = numX.multiply(numY).setScale(5, RoundingMode.HALF_UP);
          break;
        case "div":
          result = numX
              .divide(numY, 5, RoundingMode.HALF_UP);
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
