package com.bootcamp.demo.bc_calculator.controller;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bootcamp.demo.bc_calculator.dto.CalculationResult;
import com.bootcamp.demo.bc_calculator.dto.CalculatorInput;
import com.bootcamp.demo.bc_calculator.dto.Response;
import com.bootcamp.demo.bc_calculator.entity.RecordEntity;
import com.bootcamp.demo.bc_calculator.repository.RecordRepository;
import com.bootcamp.demo.bc_calculator.service.CalculatorService;

@Controller
@ResponseBody
public class CalculatorController {
  @Autowired
  private CalculatorService calculatorServicel;
  @Autowired
  RecordRepository recordRepository;

  @GetMapping(value = "/operation")
  public CalculationResult operatePara(@RequestParam String x, @RequestParam String y,
      @RequestParam String operation) {
    return validResponse(x, y, operation);
  }

  @GetMapping(value = "/operation/{x}/{y}/{operation}")
  public CalculationResult operatePath(@PathVariable String x, @PathVariable String y,
      @PathVariable String operation) {
    return validResponse(x, y, operation);
  }

  @PostMapping(value = "/operation")
  public CalculationResult operateBody(@RequestBody CalculatorInput input) {
    return validResponse(input.getX(), input.getY(), input.getOperation());
  }

  private Response validResponse(String x, String y, String operation) {
    RecordEntity newRecord = RecordEntity.builder().x(x).y(y).operation(operation).build();
    BigDecimal result = this.calculatorServicel.calculate(x, y, operation);
    newRecord.setResult(result.toPlainString());
    this.recordRepository.save(newRecord);
    return Response.builder().x(x.trim().replaceAll(" ", "")).y(y.trim().replaceAll(" ", ""))
        .operation(operation.trim()).result(result.toPlainString()).build();
  }

}
