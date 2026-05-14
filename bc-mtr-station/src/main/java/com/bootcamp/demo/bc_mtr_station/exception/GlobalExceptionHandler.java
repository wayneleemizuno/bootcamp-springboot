package com.bootcamp.demo.bc_mtr_station.exception;

import com.bootcamp.demo.bc_mtr_station.dto.ErrorResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(DataUnavailableException.class)
  public ErrorResponseDto handleDUE() {
    return new ErrorResponseDto("The data is currently unavailable.");
  }

  @ExceptionHandler(NoSuchLineException.class)
  public ErrorResponseDto handleNLE() {
    return new ErrorResponseDto("This line doesn't exist.");
  }

  @ExceptionHandler(NoSuchStationException.class)
  public ErrorResponseDto handleNSE() {
    return new ErrorResponseDto("This station doesn't exist.");
  }
}
