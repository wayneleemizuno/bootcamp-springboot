package com.bootcamp.demo.bc_forum.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ForumOperation {

  @GetMapping(value = "/userdetail")
  List<UserDetailDto> getUserDetails();

  @GetMapping(value = "/usercomments")
  UserCommentDto getUserComments(@RequestParam Long userId);

  @PostMapping(value = "/all_data")
  List<UserEntity> saveAllData();

  @GetMapping(value = "/db/users")
  List<UserEntity> getAllDbUsers();

  @GetMapping(value = "user")
  UserEntity getDbUserById(@RequestParam Long id);

  @PutMapping(value = "user/id/{id}")
  UserEntity replaceUserById(@RequestBody UserDto userDto, @PathVariable Long id);

  @GetMapping(value = "/posts")
  List<PostEntity> getAllDbPosts();

  @GetMapping(value = "/posts/user_id/{userId}")
  List<PostEntity> getPostsByUserId(@PathVariable Long userId);

  @PostMapping(value = "post/{userId}")
  PostEntity addPost(@PathVariable Long userId, @RequestBody PostDto postDto);

  @DeleteMapping(value = "post/{postId}")
  PostEntity deletePost(@PathVariable Long postId);

  @GetMapping(value = "/comments")
  List<CommentEntity> getAllComments();

  @GetMapping(value = "/comments/post")
  List<CommentEntity> getCommentsByPostId(@RequestParam Long forumPostId);

  @PostMapping(value = "/comment")
  CommentEntity addComment(@RequestParam Long forumPostId, @RequestBody CommentDto commentDto);

  @PatchMapping(value = "/comment")
  CommentEntity updateComment(@RequestParam Long commentId, @RequestBody CommentReq commentReq);
}
