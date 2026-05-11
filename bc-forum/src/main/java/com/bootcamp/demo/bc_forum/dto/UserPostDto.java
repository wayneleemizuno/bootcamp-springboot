package com.bootcamp.demo.bc_forum.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserPostDto {
  private Long id;
  private String title;
  private String body;
  @Setter private List<PostCommentDto> comments;
}
