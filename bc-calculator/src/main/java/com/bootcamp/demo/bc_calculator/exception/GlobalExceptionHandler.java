package com.bootcamp.demo.bc_calculator.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.bootcamp.demo.bc_calculator.dto.InvalidResponse;
import com.bootcamp.demo.bc_calculator.entity.RecordEntity;
import com.bootcamp.demo.bc_calculator.repository.RecordRepository;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @Autowired
  RecordRepository recordRepository;

  @ExceptionHandler(BusinessException.class)

  public InvalidResponse handleBusinessException(BusinessException e) {
    RecordEntity errRecord = RecordEntity.builder().code(e.getCode()).message(e.getMessage())
        .x(e.getX()).y(e.getY()).operation(e.getOperation()).build();
    this.recordRepository.save(errRecord);

    return InvalidResponse.builder().code(e.getCode()).message(e.getMessage()).build();
  }



}
