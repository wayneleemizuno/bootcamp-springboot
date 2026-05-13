package com.bootcamp.demo.bc_forum.service;

import com.bootcamp.demo.bc_forum.dto.CommentReq;
import com.bootcamp.demo.bc_forum.dto.UserCommentDto;
import com.bootcamp.demo.bc_forum.dto.UserDetailDto;
import com.bootcamp.demo.bc_forum.entity.CommentEntity;
import com.bootcamp.demo.bc_forum.entity.PostEntity;
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

  UserCommentDto getUserComments(Long forumUserId);

  List<UserEntity> getAllDbUsers();

  UserEntity getDbUserById(Long forumUserId);

  UserEntity replaceDbUser(UserDto userDto, Long tbrId);

  List<PostEntity> getAllDbPosts();

  List<PostEntity> getPostsByUserId(Long forumUserId);

  PostEntity addPost(PostDto postDto, Long forumUserId);

  PostEntity deletePost(Long forumPostId);

  List<CommentEntity> getAllComments();

  List<CommentEntity> getCommentsByPostId(Long forumPostId);

  CommentEntity addComment(CommentDto commentDto, Long forumPostId);

  CommentEntity updateComment(Long commentId, CommentReq commentReq);
}
