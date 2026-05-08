package com.bootcamp.demo.demo_call_api.mapper;

import com.bootcamp.demo.demo_call_api.entity.AddressEntity;
import com.bootcamp.demo.demo_call_api.entity.CompanyEntity;
import com.bootcamp.demo.demo_call_api.entity.UserEntity;
import com.bootcamp.demo.demo_call_api.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {
  public UserEntity map(UserDto userDto) {
    return UserEntity.builder()
        .forumUserId(userDto.getId())
        .name(userDto.getName())
        .username(userDto.getUsername())
        .email(userDto.getEmail())
        .phone(userDto.getPhone())
        .website(userDto.getWebsite())
        .build();
  }

  public AddressEntity map(UserDto.Address userDto) {
    return AddressEntity.builder()
        .street(userDto.getStreet())
        .suite(userDto.getSuite())
        .city(userDto.getCity())
        .zipcode(userDto.getZipcode())
        .latitude(Double.valueOf(userDto.getGeo().getLat()))
        .longitude(Double.valueOf(userDto.getGeo().getLng()))
        .build();
  }

  public CompanyEntity map(UserDto.Company userDto) {
    return CompanyEntity.builder()
        .name(userDto.getName())
        .catchPhrase(userDto.getCatchPhrase())
        .bs(userDto.getBs())
        .build();
  }
}
