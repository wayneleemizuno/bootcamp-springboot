package com.bootcamp.demo.bc_forum.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCommentDto {
  private Long userId;
  private String username;
  private UserComment[] userComments;

  @Getter
  @Builder
  public static class UserComment {
    private String name;
    private String email;
    private String body;
  }
}
