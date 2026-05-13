package com.bootcamp.demo.bc_forum.exception;

import com.bootcamp.demo.bc_forum.dto.ErrorDto;
import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NoSuchElementException.class)
  public ErrorDto handleNSE() {
    return ErrorDto.builder()
        .code(ErrorTypes.TARGET_NOT_FOUND.getCode())
        .message(ErrorTypes.TARGET_NOT_FOUND.getMessage())
        .build();
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ErrorDto handleIAE() {
    return ErrorDto.builder()
        .code(ErrorTypes.INVALID_ID.getCode())
        .message(ErrorTypes.INVALID_ID.getMessage())
        .build();
  }
}
