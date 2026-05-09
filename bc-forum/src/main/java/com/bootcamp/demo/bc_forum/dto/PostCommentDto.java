package com.bootcamp.demo.bc_forum.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostCommentDto {
  private Long id;
  private String name;
  private String email;
  private String body;
}
