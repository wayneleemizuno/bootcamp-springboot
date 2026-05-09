package com.bootcamp.demo.bc_forum.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class UserPostDto {
  private Long id;
  private String title;
  private String body;
  @Setter private PostCommentDto[] comments;
}
