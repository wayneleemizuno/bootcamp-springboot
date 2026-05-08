package com.bootcamp.demo.demo_call_api.mapper;

import com.bootcamp.demo.demo_call_api.dto.ForumUserDto;
import com.bootcamp.demo.demo_call_api.entity.UserEntity;
import com.bootcamp.demo.demo_call_api.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
  public ForumUserDto map(UserEntity userEntity) {
    return ForumUserDto.builder()
        .email(userEntity.getEmail())
        .phone(userEntity.getPhone())
        .username(userEntity.getUsername())
        .build();
  }

  public ForumUserDto map(UserDto userDto) {
    return ForumUserDto.builder()
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
