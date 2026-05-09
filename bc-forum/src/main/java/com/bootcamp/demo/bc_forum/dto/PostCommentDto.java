package com.bootcamp.demo.bc_forum.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PostCommentDto {
  private Long id;
  private String name;
  private String email;
  private String body;
}
