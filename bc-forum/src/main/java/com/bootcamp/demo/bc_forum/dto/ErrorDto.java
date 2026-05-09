package com.bootcamp.demo.bc_forum.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorDto {
  private String code;
  private String message;
}
