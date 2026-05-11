package com.bootcamp.demo.bc_forum.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCommentDto {
  private Long userId;
  private String username;
  private List<UserComment> userComments;

  @Getter
  @Builder
  public static class UserComment {
    private String name;
    private String email;
    private String body;
  }
}
