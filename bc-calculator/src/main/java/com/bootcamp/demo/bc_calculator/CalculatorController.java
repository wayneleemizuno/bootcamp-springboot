package com.bootcamp.demo.bc_calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class CalculatorController {
  @Autowired
  private CalculatorService calculatorServicel;

  @GetMapping(value = "/operation")
  public CalculationResult operatePara(@RequestParam String x, @RequestParam String y,
      @RequestParam String operation) {
    try {
      Double result = this.calculatorServicel.calculate(x, y, operation);
      return ResponseStatus.builder().x(x).y(y).operation(operation).result(result.toString())
          .build();
    } catch (BusinessException e) {
      return InvalidResponse.builder().code(e.getCode()).message(e.getMessage()).build();
    }
  }

  @PostMapping(value = "/operation")
  public CalculationResult operateBody(@RequestBody CalculatorInput input) {
    try {
      Double result =
          this.calculatorServicel.calculate(input.getX(), input.getY(), input.getOperation());
      return ResponseStatus.builder().x(input.getX()).y(input.getY())
          .operation(input.getOperation()).result(result.toString()).build();
    } catch (BusinessException e) {
      return InvalidResponse.builder().code(e.getCode()).message(e.getMessage()).build();
    }
  }

  @GetMapping(value = "/operation/{x}/{y}/{operation}")
  public CalculationResult operatePath(@PathVariable String x, @PathVariable String y,
      @PathVariable String operation) {
    try {
      Double result = this.calculatorServicel.calculate(x, y, operation);
      return ResponseStatus.builder().x(x).y(y).operation(operation).result(result.toString())
          .build();
    } catch (BusinessException e) {
      return InvalidResponse.builder().code(e.getCode()).message(e.getMessage()).build();
    }
  }

}
