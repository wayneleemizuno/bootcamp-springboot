package com.bootcamp.demo.bc_forum.mapper;

import com.bootcamp.demo.bc_forum.dto.PostCommentDto;
import com.bootcamp.demo.bc_forum.dto.UserCommentDto;
import com.bootcamp.demo.bc_forum.dto.UserDetailDto;
import com.bootcamp.demo.bc_forum.dto.UserDetailDto.Address;
import com.bootcamp.demo.bc_forum.dto.UserDetailDto.Address.Geo;
import com.bootcamp.demo.bc_forum.dto.UserDetailDto.Company;
import com.bootcamp.demo.bc_forum.dto.UserPostDto;
import com.bootcamp.demo.bc_forum.model.CommentDto;
import com.bootcamp.demo.bc_forum.model.PostDto;
import com.bootcamp.demo.bc_forum.model.UserDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
  public Geo map(UserDto.AddressDto.GeoDto geoDto) {
    return Geo.builder().lat(geoDto.getLat()).lng(geoDto.getLng()).build();
  }

  public Address map(UserDto.AddressDto addressDto, Geo geo) {
    return Address.builder()
        .street(addressDto.getStreet())
        .suite(addressDto.getSuite())
        .city(addressDto.getCity())
        .zipcode(addressDto.getZipcode())
        .geo(geo)
        .build();
  }

  public Company map(UserDto.CompanyDto companyDto) {
    return Company.builder()
        .name(companyDto.getName())
        .catchPhrase(companyDto.getCatchPhrase())
        .bs(companyDto.getBs())
        .build();
  }

  public UserDetailDto map(UserDto userDto, Address address, Company company, UserPostDto[] posts) {
    return UserDetailDto.builder()
        .id(userDto.getId())
        .name(userDto.getName())
        .username(userDto.getUsername())
        .email(userDto.getEmail())
        .address(address)
        .phone(userDto.getPhone())
        .website(userDto.getWebsite())
        .company(company)
        .posts(posts)
        .build();
  }

  public UserPostDto[] map(UserDto userDto, List<PostDto> postDtos, List<CommentDto> commentDtos) {
    List<PostDto> matchedPost =
        postDtos.stream()
            .filter(post -> post.getUserId().equals(userDto.getId()))
            .collect(Collectors.toList());
    List<UserPostDto> userPostDtos =
        matchedPost.stream()
            .map(
                e ->
                    UserPostDto.builder()
                        .id(e.getId())
                        .title(e.getTitle())
                        .body(e.getBody())
                        .build())
            .collect(Collectors.toList());

    return userPostDtos.stream()
        .map(
            userPost -> {
              List<CommentDto> matchedComments =
                  commentDtos.stream()
                      .filter(comment -> comment.getPostId().equals(userPost.getId()))
                      .collect(Collectors.toList());
              PostCommentDto[] postCommentDtos =
                  matchedComments.stream()
                      .map(
                          comment ->
                              PostCommentDto.builder()
                                  .id(comment.getId())
                                  .name(comment.getName())
                                  .email(comment.getEmail())
                                  .body(comment.getBody())
                                  .build())
                      .toArray(PostCommentDto[]::new);
              userPost.setComments(postCommentDtos);
              return userPost;
            })
        .toArray(UserPostDto[]::new);
  }

  public UserCommentDto.UserComment[] map(List<PostCommentDto> postCommentDtos) {
    return postCommentDtos.stream()
        .map(
            comment ->
                UserCommentDto.UserComment.builder()
                    .name(comment.getName())
                    .email(comment.getEmail())
                    .body(comment.getBody())
                    .build())
        .collect(Collectors.toList())
        .toArray(UserCommentDto.UserComment[]::new);
  }
}
