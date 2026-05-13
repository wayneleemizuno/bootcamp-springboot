package com.bootcamp.demo.bc_forum.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class UserPostDto {
  private Long forumPostId;
  private String title;
  private String body;
  @Setter private List<PostCommentDto> comments;
}
