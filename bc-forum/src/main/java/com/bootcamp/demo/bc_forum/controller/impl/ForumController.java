package com.bootcamp.demo.bc_forum.controller.impl;

import com.bootcamp.demo.bc_forum.controller.ForumOperation;
import com.bootcamp.demo.bc_forum.dto.CommentReq;
import com.bootcamp.demo.bc_forum.dto.UserCommentDto;
import com.bootcamp.demo.bc_forum.dto.UserDetailDto;
import com.bootcamp.demo.bc_forum.entity.CommentEntity;
import com.bootcamp.demo.bc_forum.entity.PostEntity;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import com.bootcamp.demo.bc_forum.model.CommentDto;
import com.bootcamp.demo.bc_forum.model.PostDto;
import com.bootcamp.demo.bc_forum.model.UserDto;
import com.bootcamp.demo.bc_forum.service.ForumService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForumController implements ForumOperation {
  @Autowired ForumService forumService;

  @Override
  public List<UserDetailDto> getUserDetails() {
    return this.forumService.getUserDetails();
  }

  @Override
  public UserCommentDto getUserComments(Long userId) {
    return this.forumService.getUserComments(userId);
  }

  @Override
  public List<UserEntity> saveAllData() {
    return this.forumService.saveAllData();
  }

  @Override
  public List<UserEntity> getAllDbUsers() {
    return this.forumService.getAllDbUsers();
  }

  @Override
  public UserEntity getDbUserById(Long id) {
    return this.forumService.getDbUserById(id);
  }

  @Override
  public UserEntity replaceUserById(UserDto userDto, Long id) {
    return this.forumService.replaceDbUser(userDto, id);
  }

  @Override
  public List<PostEntity> getAllDbPosts() {
    return this.forumService.getAllDbPosts();
  }

  @Override
  public List<PostEntity> getPostsByUserId(Long userId) {
    return this.forumService.getPostsByUserId(userId);
  }

  @Override
  public PostEntity addPost(Long userId, PostDto postDto) {
    return this.forumService.addPost(postDto, userId);
  }

  @Override
  public PostEntity deletePost(Long postId) {
    return this.forumService.deletePost(postId);
  }

  @Override
  public List<CommentEntity> getAllComments() {
    return this.forumService.getAllComments();
  }

  @Override
  public List<CommentEntity> getCommentsByPostId(@RequestParam Long forumPostId) {
    return this.forumService.getCommentsByPostId(forumPostId);
  }

  @Override
  public CommentEntity addComment(
      @RequestParam Long forumPostId, @RequestBody CommentDto commentDto) {
    return this.forumService.addComment(commentDto, forumPostId);
  }

  @Override
  public CommentEntity updateComment(Long commentId, CommentReq commentReq) {
    return this.forumService.updateComment(commentId, commentReq);
  }
}
