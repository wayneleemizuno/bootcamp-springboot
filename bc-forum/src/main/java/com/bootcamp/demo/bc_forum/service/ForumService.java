package com.bootcamp.demo.bc_forum.service;

import com.bootcamp.demo.bc_forum.dto.UserCommentDto;
import com.bootcamp.demo.bc_forum.dto.UserDetailDto;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import com.bootcamp.demo.bc_forum.model.CommentDto;
import com.bootcamp.demo.bc_forum.model.PostDto;
import com.bootcamp.demo.bc_forum.model.UserDto;
import java.util.List;

public interface ForumService {
  List<UserDto> getUsers();

  List<PostDto> getPosts();

  List<CommentDto> getComments();

  List<UserEntity> saveAllData();

  List<UserDetailDto> getUserDetails();

  UserCommentDto getUserComments(Long userId);
}
