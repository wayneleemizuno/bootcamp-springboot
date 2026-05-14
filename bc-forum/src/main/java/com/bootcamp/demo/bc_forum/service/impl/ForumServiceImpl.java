package com.bootcamp.demo.bc_forum.service.impl;

import com.bootcamp.demo.bc_forum.dto.CommentReq;
import com.bootcamp.demo.bc_forum.dto.PostCommentDto;
import com.bootcamp.demo.bc_forum.dto.UserCommentDto;
import com.bootcamp.demo.bc_forum.dto.UserDetailDto;
import com.bootcamp.demo.bc_forum.dto.UserPostDto;
import com.bootcamp.demo.bc_forum.entity.AddressEntity;
import com.bootcamp.demo.bc_forum.entity.CommentEntity;
import com.bootcamp.demo.bc_forum.entity.CompanyEntity;
import com.bootcamp.demo.bc_forum.entity.PostEntity;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ForumServiceImpl implements ForumService {
  @Autowired UserRepository userRepository;
  @Autowired CommentRepository commentRepository;
  @Autowired PostRepository postRepository;
  @Autowired CompanyRepository companyRepository;
  @Autowired AddressRepository addressRepository;
  @Autowired DtoMapper dtoMapper;
  @Autowired RestTemplate restTemplate;
  @Autowired EntityMapper entityMapper;

  @Value("${api.jsonplaceholder.domain}")
  private String domain;

  @Value("${api.jsonplaceholder.path.user}")
  private String userPath;

  @Value("${api.jsonplaceholder.path.post}")
  private String postPath;

  @Value("${api.jsonplaceholder.path.comment}")
  private String commentPath;

  @Override
  public List<UserDto> getUsers() {
    String url =
        UriComponentsBuilder.newInstance()
            .scheme("https")
            .host(this.domain)
            .path(this.userPath)
            .build()
            .toUriString();
    UserDto[] userDtos = this.restTemplate.getForObject(url, UserDto[].class);
    return Arrays.asList(userDtos);
  }

  @Override
  public List<PostDto> getPosts() {
    String url =
        UriComponentsBuilder.newInstance()
            .scheme("https")
            .host(this.domain)
            .path(this.postPath)
            .build()
            .toUriString();
    PostDto[] postDtos = this.restTemplate.getForObject(url, PostDto[].class);
    return Arrays.asList(postDtos);
  }

  @Override
  public List<CommentDto> getComments() {
    String url =
        UriComponentsBuilder.newInstance()
            .scheme("https")
            .host(this.domain)
            .path(this.commentPath)
            .build()
            .toUriString();
    CommentDto[] CommentDtos = this.restTemplate.getForObject(url, CommentDto[].class);
    return Arrays.asList(CommentDtos);
  }

  @Override
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

  @Override
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

  @Override
  public UserCommentDto getUserComments(Long forumUserId) {
    List<UserDetailDto> users = this.getUserDetails();
    UserDetailDto matchedUser =
        users.stream().filter(u -> u.getForumUserId().equals(forumUserId)).findFirst().get();

    List<UserPostDto> userPostDtos = matchedUser.getPosts().stream().collect(Collectors.toList());

    List<PostCommentDto> postCommentDtos =
        userPostDtos.stream()
            .flatMap(post -> post.getComments().stream())
            .collect(Collectors.toList());

    return UserCommentDto.builder()
        .forumUserId(matchedUser.getForumUserId())
        .username(matchedUser.getUsername())
        .userComments(this.dtoMapper.map(postCommentDtos))
        .build();
  }

  @Override
  public List<UserEntity> getAllDbUsers() {
    return this.userRepository.findAll();
  }

  @Override
  public UserEntity getDbUserById(Long forumUserId) {
    return this.userRepository.findByForumUserId(forumUserId).get();
  }

  @Override
  public UserEntity replaceDbUser(UserDto userDto, Long tbrId) {
    UserEntity tbrUser = this.userRepository.findById(tbrId).get();
    UserEntity newUser = this.entityMapper.mapNu(userDto, tbrUser.getId());

    Long tbrAddressId = this.addressRepository.findByUserEntity(tbrUser).get().getId();
    AddressEntity newAdd = this.entityMapper.mapNa(userDto, tbrAddressId);
    newAdd.setUserEntity(newUser);

    Long tbrCompanyId = this.companyRepository.findByUserEntity(tbrUser).get().getId();
    CompanyEntity newCompany = this.entityMapper.mapNc(userDto, tbrCompanyId);
    newCompany.setUserEntity(newUser);

    this.userRepository.save(newUser);
    this.addressRepository.save(newAdd);
    this.companyRepository.save(newCompany);
    return newUser;
  }

  @Override
  public List<PostEntity> getAllDbPosts() {
    return this.postRepository.findAll();
  }

  @Override
  public List<PostEntity> getPostsByUserId(Long forumUserId) {
    UserEntity matchedUser = this.userRepository.findByForumUserId(forumUserId).get();
    return this.postRepository.findAllByUserEntity(matchedUser);
  }

  @Override
  public PostEntity addPost(PostDto postDto, Long userId) {
    UserEntity matchedUser = this.userRepository.findByForumUserId(userId).get();
    PostEntity newPost = this.entityMapper.mapNp(postDto, matchedUser.getForumUserId());
    newPost.setUserEntity(matchedUser);
    return this.postRepository.save(newPost);
  }

  @Override
  public PostEntity deletePost(Long postId) {
    PostEntity targetPost = this.postRepository.findByForumPostId(postId).get();
    this.commentRepository.deleteByForumPostId(postId);
    this.postRepository.deleteByForumPostId(postId);
    return targetPost;
  }

  @Override
  public List<CommentEntity> getAllComments() {
    return this.commentRepository.findAll();
  }

  @Override
  public List<CommentEntity> getCommentsByPostId(Long forumPostId) {
    return this.commentRepository.findAllByForumPostId(forumPostId);
  }

  @Override
  public CommentEntity addComment(CommentDto commentDto, Long postId) {
    PostEntity targetPost = this.postRepository.findByForumPostId(postId).get();
    CommentEntity newComment = this.entityMapper.mapNcm(commentDto, postId);
    newComment.setPostEntity(targetPost);
    return this.commentRepository.save(newComment);
  }

  @Override
  public CommentEntity updateComment(Long commentId, CommentReq commentReq) {
    CommentEntity targetComment = this.commentRepository.findById(commentId).get();
    targetComment.setBody(commentReq.getBody());
    return this.commentRepository.save(targetComment);
  }
}
