package com.bootcamp.demo.bc_forum.service.impl;

import com.bootcamp.demo.bc_forum.config.CommentAPI;
import com.bootcamp.demo.bc_forum.config.PostAPI;
import com.bootcamp.demo.bc_forum.config.UserAPI;
import com.bootcamp.demo.bc_forum.dto.PostCommentDto;
import com.bootcamp.demo.bc_forum.dto.UserCommentDto;
import com.bootcamp.demo.bc_forum.dto.UserDetailDto;
import com.bootcamp.demo.bc_forum.dto.UserPostDto;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import com.bootcamp.demo.bc_forum.mapper.DtoMapper;
import com.bootcamp.demo.bc_forum.mapper.EntityMapper;
import com.bootcamp.demo.bc_forum.model.CommentDto;
import com.bootcamp.demo.bc_forum.model.PostDto;
import com.bootcamp.demo.bc_forum.model.UserDto;
import com.bootcamp.demo.bc_forum.repository.AddressRepository;
import com.bootcamp.demo.bc_forum.repository.CommentRepository;
import com.bootcamp.demo.bc_forum.repository.CompanyRepository;
import com.bootcamp.demo.bc_forum.repository.PostRepository;
import com.bootcamp.demo.bc_forum.repository.UserRepository;
import com.bootcamp.demo.bc_forum.service.ForumService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ForumServiceImpl implements ForumService {
  @Autowired UserRepository userRepository;
  @Autowired CommentRepository commentRepository;
  @Autowired PostRepository postRepository;
  @Autowired CompanyRepository companyRepository;
  @Autowired AddressRepository addressRepository;
  @Autowired DtoMapper dtoMapper;
  @Autowired RestTemplate restTemplate;
  @Autowired UserAPI userAPI;
  @Autowired PostAPI postAPI;
  @Autowired CommentAPI commentAPI;
  @Autowired EntityMapper entityMapper;

  public List<UserDto> getUsers() {
    UserDto[] userDtos = this.restTemplate.getForObject(this.userAPI.getUrl(), UserDto[].class);
    return Arrays.asList(userDtos);
  }

  public List<PostDto> getPosts() {
    PostDto[] postDtos = this.restTemplate.getForObject(this.postAPI.getUrl(), PostDto[].class);
    return Arrays.asList(postDtos);
  }

  public List<CommentDto> getComments() {
    CommentDto[] CommentDtos =
        this.restTemplate.getForObject(this.commentAPI.getUrl(), CommentDto[].class);
    return Arrays.asList(CommentDtos);
  }

  public List<UserEntity> saveAllData() {
    this.addressRepository.deleteAll();
    this.companyRepository.deleteAll();
    this.commentRepository.deleteAll();
    this.postRepository.deleteAll();
    this.userRepository.deleteAll();
    List<UserDto> userDtos = this.getUsers();
    List<PostDto> postDtos = this.getPosts();
    List<CommentDto> commentDtos = this.getComments();
    return this.entityMapper.mapAndSave(userDtos, postDtos, commentDtos);
  }

  public List<UserDetailDto> getUserDetails() {
    List<UserDto> userDtos = this.getUsers();
    List<PostDto> postDtos = this.getPosts();
    List<CommentDto> commentDtos = this.getComments();

    return userDtos.stream()
        .map(
            u -> {
              List<UserPostDto> userPostDtos = this.dtoMapper.map(u, postDtos, commentDtos);
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

    List<UserPostDto> userPostDtos = matchedUser.getPosts().stream().collect(Collectors.toList());

    List<PostCommentDto> postCommentDtos =
        userPostDtos.stream()
            .flatMap(post -> post.getComments().stream())
            .collect(Collectors.toList());

    return UserCommentDto.builder()
        .userId(matchedUser.getId())
        .username(matchedUser.getUsername())
        .userComments(this.dtoMapper.map(postCommentDtos))
        .build();
  }
}
