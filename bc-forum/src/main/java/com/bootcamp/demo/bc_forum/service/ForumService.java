package com.bootcamp.demo.bc_forum.service;

import com.bootcamp.demo.bc_forum.dto.PostCommentDto;
import com.bootcamp.demo.bc_forum.dto.UserCommentDto;
import com.bootcamp.demo.bc_forum.dto.UserDetailDto;
import com.bootcamp.demo.bc_forum.dto.UserPostDto;
import com.bootcamp.demo.bc_forum.mapper.DtoMapper;
import com.bootcamp.demo.bc_forum.model.CommentDto;
import com.bootcamp.demo.bc_forum.model.PostDto;
import com.bootcamp.demo.bc_forum.model.UserDto;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ForumService {
  @Autowired DtoMapper dtoMapper;

  public List<UserDto> getUsers() {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/users";
    UserDto[] userDtos = restTemplate.getForObject(url, UserDto[].class);
    return Arrays.asList(userDtos);
  }

  public List<PostDto> getPosts() {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/posts";
    PostDto[] postDtos = restTemplate.getForObject(url, PostDto[].class);
    return Arrays.asList(postDtos);
  }

  public List<CommentDto> getComments() {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/comments";
    CommentDto[] CommentDtos = restTemplate.getForObject(url, CommentDto[].class);
    return Arrays.asList(CommentDtos);
  }

  public List<UserDetailDto> getUserDetails() {
    List<UserDto> userDtos = this.getUsers();
    List<PostDto> postDtos = this.getPosts();
    List<CommentDto> commentDtos = this.getComments();

    return userDtos.stream()
        .map(
            u -> {
              UserPostDto[] userPostDtos = this.dtoMapper.map(u, postDtos, commentDtos);
              UserDetailDto.Address.Geo geo = this.dtoMapper.map(u.getAddressDto().getGeoDto());
              UserDetailDto.Address address = this.dtoMapper.map(u.getAddressDto(), geo);
              UserDetailDto.Company company = this.dtoMapper.map(u.getCompanyDto());
              return this.dtoMapper.map(u, address, company, userPostDtos);
            })
        .collect(Collectors.toList());
  }

  public UserCommentDto getUserComments(Long userId) {
    List<UserDetailDto> users = this.getUserDetails();
    UserDetailDto matchedUser =
        users.stream().filter(u -> u.getId().equals(userId)).findFirst().get();

    List<UserPostDto> userPostDtos =
        Arrays.asList(matchedUser.getPosts()).stream().collect(Collectors.toList());

    List<PostCommentDto> postCommentDtos =
        userPostDtos.stream()
            .flatMap(post -> Arrays.stream(post.getComments()))
            .collect(Collectors.toList());

    return UserCommentDto.builder()
        .userId(matchedUser.getId())
        .username(matchedUser.getUsername())
        .userComments(this.dtoMapper.map(postCommentDtos))
        .build();
  }
}
