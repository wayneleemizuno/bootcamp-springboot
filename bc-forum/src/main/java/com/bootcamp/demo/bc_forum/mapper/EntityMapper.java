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

  public void mapAndSave(
      List<UserDto> userDtos, List<PostDto> postDtos, List<CommentDto> commentDtos) {
    List<UserEntity> userEntities = new LinkedList<>();
    List<AddressEntity> addressEntities = new LinkedList<>();
    List<CompanyEntity> companyEntities = new LinkedList<>();
    userDtos.stream()
        .map(
            u -> {
              UserEntity userEntity =
                  UserEntity.builder()
                      .name(u.getName())
                      .username(u.getUsername())
                      .email(u.getEmail())
                      .phone(u.getPhone())
                      .website(u.getWebsite())
                      .build();
              userEntities.add(userEntity);

              AddressEntity addressEntity =
                  AddressEntity.builder()
                      .street(u.getAddressDto().getStreet())
                      .suite(u.getAddressDto().getSuite())
                      .city(u.getAddressDto().getCity())
                      .zipcode(u.getAddressDto().getZipcode())
                      .lat(u.getAddressDto().getGeoDto().getLat())
                      .lng(u.getAddressDto().getGeoDto().getLng())
                      .build();
              addressEntity.setUserEntity(userEntity);
              addressEntities.add(addressEntity);

              CompanyEntity companyEntity =
                  CompanyEntity.builder()
                      .name(u.getCompanyDto().getName())
                      .catchPhrase(u.getCompanyDto().getCatchPhrase())
                      .bs(u.getCompanyDto().getBs())
                      .build();
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
                          .filter(u -> u.getId().equals(p.getUserId()))
                          .findFirst()
                          .get();
                  PostEntity postEntity =
                      PostEntity.builder().title(p.getTitle()).body(p.getBody()).build();
                  postEntity.setUserEntity(matchedUser);
                  return postEntity;
                })
            .collect(Collectors.toList());
    this.postRepository.saveAll(postEntities);

    List<CommentEntity> commentEntities =
        commentDtos.stream()
            .map(
                c -> {
                  PostEntity matchedPost =
                      postEntities.stream()
                          .filter(p -> p.getId().equals(c.getPostId()))
                          .findFirst()
                          .get();
                  CommentEntity commentEntity =
                      CommentEntity.builder()
                          .name(c.getName())
                          .email(c.getEmail())
                          .body(c.getBody())
                          .build();
                  commentEntity.setPostEntity(matchedPost);
                  return commentEntity;
                })
            .collect(Collectors.toList());
    this.commentRepository.saveAll(commentEntities);
  }
}
