package com.bootcamp.demo.demo_call_api.mapper;

import com.bootcamp.demo.demo_call_api.dto.ForumUserResp;
import com.bootcamp.demo.demo_call_api.entity.UserEntity;
import com.bootcamp.demo.demo_call_api.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
  public ForumUserResp map(UserEntity userEntity) {
    return ForumUserResp.builder()
        .email(userEntity.getEmail())
        .phone(userEntity.getPhone())
        .username(userEntity.getUsername())
        .build();
  }

  public ForumUserResp map(UserDto userDto) {
    return ForumUserResp.builder()
        .email(userDto.getEmail())
        .phone(userDto.getPhone())
        .username(userDto.getUsername())
        .build();
  }

  // public DbUserDto map(UserDto userDto) {
  //   return DbUserDto.builder()
  //       .user_id(userDto.getId())
  //       .user_name(userDto.getUsername())
  //       .name(userDto.getName())
  //       .phone(userDto.getPhone())
  //       .email(userDto.getEmail())
  //       .build();
  // }
}
