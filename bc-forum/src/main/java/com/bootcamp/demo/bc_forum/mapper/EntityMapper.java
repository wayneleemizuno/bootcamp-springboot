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
import java.util.stream.Collectors;
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

  public List<UserEntity> mapAndSave(
      List<UserDto> userDtos, List<PostDto> postDtos, List<CommentDto> commentDtos) {
    List<UserEntity> userEntities = new LinkedList<>();
    List<AddressEntity> addressEntities = new LinkedList<>();
    List<CompanyEntity> companyEntities = new LinkedList<>();
    userDtos.stream()
        .map(
            u -> {
              UserEntity userEntity = this.mapU(u);
              userEntities.add(userEntity);

              AddressEntity addressEntity = this.mapA(u);
              addressEntity.setUserEntity(userEntity);
              addressEntities.add(addressEntity);

              CompanyEntity companyEntity = this.mapC(u);
              companyEntity.setUserEntity(userEntity);
              companyEntities.add(companyEntity);
              return userEntity;
            })
        .collect(Collectors.toList());
    this.userRepository.saveAll(userEntities);
    this.addressRepository.saveAll(addressEntities);
    this.companyRepository.saveAll(companyEntities);

    List<PostEntity> postEntities =
        postDtos.stream()
            .map(
                p -> {
                  UserEntity matchedUser =
                      userEntities.stream()
                          .filter(u -> u.getForumUserId().equals(p.getUserId()))
                          .findFirst()
                          .get();
                  PostEntity postEntity =
                      PostEntity.builder()
                          .forumPostId(p.getId())
                          .forumUserId(p.getUserId())
                          .title(p.getTitle())
                          .body(p.getBody())
                          .userEntity(matchedUser)
                          .build();
                  return postEntity;
                })
            .collect(Collectors.toList());

    List<CommentEntity> commentEntities =
        commentDtos.stream()
            .map(
                c -> {
                  PostEntity matchedPost =
                      postEntities.stream()
                          .filter(p -> p.getForumPostId().equals(c.getPostId()))
                          .findFirst()
                          .get();
                  CommentEntity commentEntity =
                      CommentEntity.builder()
                          .forumPostId(c.getPostId())
                          .name(c.getName())
                          .email(c.getEmail())
                          .body(c.getBody())
                          .postEntity(matchedPost)
                          .build();
                  return commentEntity;
                })
            .collect(Collectors.toList());

    this.postRepository.saveAll(postEntities);
    this.commentRepository.saveAll(commentEntities);

    return userEntities;
  }
}
