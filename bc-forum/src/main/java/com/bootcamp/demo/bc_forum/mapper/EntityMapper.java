package com.bootcamp.demo.bc_forum.mapper;

import com.bootcamp.demo.bc_forum.entity.AddressEntity;
import com.bootcamp.demo.bc_forum.entity.CommentEntity;
import com.bootcamp.demo.bc_forum.entity.CompanyEntity;
import com.bootcamp.demo.bc_forum.entity.PostEntity;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import com.bootcamp.demo.bc_forum.model.CommentDto;
import com.bootcamp.demo.bc_forum.model.PostDto;
import com.bootcamp.demo.bc_forum.model.UserDto;
import com.bootcamp.demo.bc_forum.repository.AddressRepository;
import com.bootcamp.demo.bc_forum.repository.CommentRepository;
import com.bootcamp.demo.bc_forum.repository.CompanyRepository;
import com.bootcamp.demo.bc_forum.repository.PostRepository;
import com.bootcamp.demo.bc_forum.repository.UserRepository;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {
  @Autowired UserRepository userRepository;
  @Autowired AddressRepository addressRepository;
  @Autowired CompanyRepository companyRepository;
  @Autowired PostRepository postRepository;
  @Autowired CommentRepository commentRepository;

  public UserEntity mapU(UserDto userDto) {
    return UserEntity.builder()
        .name(userDto.getName())
        .username(userDto.getUsername())
        .forumUserId(userDto.getId())
        .email(userDto.getEmail())
        .phone(userDto.getPhone())
        .website(userDto.getWebsite())
        .build();
  }

  public AddressEntity mapA(UserDto userDto) {
    return AddressEntity.builder()
        .street(userDto.getAddressDto().getStreet())
        .suite(userDto.getAddressDto().getSuite())
        .city(userDto.getAddressDto().getCity())
        .zipcode(userDto.getAddressDto().getZipcode())
        .lat(userDto.getAddressDto().getGeoDto().getLat())
        .lng(userDto.getAddressDto().getGeoDto().getLng())
        .build();
  }

  public CompanyEntity mapC(UserDto userDto) {
    return CompanyEntity.builder()
        .name(userDto.getCompanyDto().getName())
        .catchPhrase(userDto.getCompanyDto().getCatchPhrase())
        .bs(userDto.getCompanyDto().getBs())
        .build();
  }

  public PostEntity mapP(PostDto postDto, UserEntity userEntity) {
    return PostEntity.builder()
        .forumPostId(postDto.getId())
        .forumUserId(postDto.getUserId())
        .title(postDto.getTitle())
        .body(postDto.getBody())
        .userEntity(userEntity)
        .build();
  }

  public CommentEntity mapC(CommentDto commentDto, PostEntity postEntity) {
    return CommentEntity.builder()
        .forumPostId(commentDto.getPostId())
        .name(commentDto.getName())
        .email(commentDto.getEmail())
        .body(commentDto.getBody())
        .postEntity(postEntity)
        .build();
  }

  public List<UserEntity> mapAndSave(
      List<UserDto> userDtos, List<PostDto> postDtos, List<CommentDto> commentDtos) {

    List<UserEntity> userEntities = new LinkedList<>();
    List<AddressEntity> addressEntities = new LinkedList<>();
    List<CompanyEntity> companyEntities = new LinkedList<>();
    List<PostEntity> postEntities = new LinkedList<>();
    List<CommentEntity> commentEntities = new LinkedList<>();

    userDtos.stream()
        .forEach(
            u -> {
              UserEntity userEntity = this.mapU(u);
              userEntities.add(userEntity);

              AddressEntity addressEntity = this.mapA(u);
              addressEntity.setUserEntity(userEntity);
              addressEntities.add(addressEntity);

              CompanyEntity companyEntity = this.mapC(u);
              companyEntity.setUserEntity(userEntity);
              companyEntities.add(companyEntity);

              postDtos.stream()
                  .filter(p -> p.getUserId().equals(userEntity.getForumUserId()))
                  .forEach(
                      p -> {
                        PostEntity postEntity = this.mapP(p, userEntity);
                        postEntities.add(postEntity);

                        commentDtos.stream()
                            .filter(c -> c.getPostId().equals(postEntity.getForumPostId()))
                            .forEach(
                                c -> {
                                  CommentEntity commentEntity = this.mapC(c, postEntity);
                                  commentEntities.add(commentEntity);
                                });
                      });
            });
    this.userRepository.saveAll(userEntities);
    this.addressRepository.saveAll(addressEntities);
    this.companyRepository.saveAll(companyEntities);
    this.postRepository.saveAll(postEntities);
    this.commentRepository.saveAll(commentEntities);

    return userEntities;
  }

  public UserEntity mapNu(UserDto userDto, Long tbrId) {
    return UserEntity.builder()
        .id(tbrId)
        .forumUserId(userDto.getId())
        .name(userDto.getName())
        .username(userDto.getUsername())
        .email(userDto.getEmail())
        .phone(userDto.getPhone())
        .website(userDto.getWebsite())
        .build();
  }

  public AddressEntity mapNa(UserDto userDto, Long tbrId) {
    return AddressEntity.builder()
        .id(tbrId)
        .street(userDto.getAddressDto().getStreet())
        .suite(userDto.getAddressDto().getSuite())
        .city(userDto.getAddressDto().getCity())
        .zipcode(userDto.getAddressDto().getZipcode())
        .lat(userDto.getAddressDto().getGeoDto().getLat())
        .lng(userDto.getAddressDto().getGeoDto().getLng())
        .build();
  }

  public CompanyEntity mapNc(UserDto userDto, Long tbrId) {
    return CompanyEntity.builder()
        .id(tbrId)
        .name(userDto.getCompanyDto().getName())
        .catchPhrase(userDto.getCompanyDto().getCatchPhrase())
        .bs(userDto.getCompanyDto().getBs())
        .build();
  }

  public PostEntity mapNp(PostDto postDto, Long forumUserId) {
    return PostEntity.builder()
        .forumUserId(forumUserId)
        .forumPostId(postDto.getId())
        .title(postDto.getTitle())
        .body(postDto.getBody())
        .build();
  }

  public CommentEntity mapNcm(CommentDto commentDto, Long forumPostId) {
    return CommentEntity.builder()
        .forumPostId(commentDto.getPostId())
        .name(commentDto.getName())
        .email(commentDto.getEmail())
        .body(commentDto.getBody())
        .build();
  }
}
