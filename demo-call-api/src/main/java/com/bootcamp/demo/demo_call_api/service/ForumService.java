package com.bootcamp.demo.demo_call_api.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.demo_call_api.entity.AddressEntity;
import com.bootcamp.demo.demo_call_api.entity.CompanyEntity;
import com.bootcamp.demo.demo_call_api.entity.UserEntity;
import com.bootcamp.demo.demo_call_api.model.UserDto;
import com.bootcamp.demo.demo_call_api.repository.AddressRepository;
import com.bootcamp.demo.demo_call_api.repository.CompanyRepository;
import com.bootcamp.demo.demo_call_api.repository.UserRepository;

@Service
public class ForumService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  AddressRepository addressRepository;
  @Autowired
  CompanyRepository companyRepository;

  public List<UserDto> getUsers() {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/users";

    // * Deserialisation
    // in JSON([]), List = Array
    UserDto[] userDtos = restTemplate.getForObject(url, UserDto[].class); // =RESTful GET
    return Arrays.asList(userDtos);
  }

  // TD: create new DTO to return
  public List<UserEntity> postUsers() {
    return this.getUsers().stream().map(u -> {
      AddressEntity addressEntry = mapAddress(u);
      CompanyEntity companyEntry = mapCompany(u);
      UserEntity userEntry = mapUser(u, addressEntry, companyEntry);
      addressEntry.setUserEntity(userEntry);
      companyEntry.setUserEntity(userEntry);
      return this.userRepository.save(userEntry);
    }).collect(Collectors.toList());
  }

  public List<UserEntity> getDbUsers() {
    return this.userRepository.findAll();
  }

  private UserEntity mapUser(UserDto userDto, AddressEntity address, CompanyEntity company) {
    return UserEntity.builder()//
        .forumUserId(userDto.getId())//
        .name(userDto.getName())//
        .username(userDto.getUsername())//
        .email(userDto.getEmail())//
        .phone(userDto.getPhone())//
        .website(userDto.getWebsite())//
        .address(address)//
        .company(company)//
        .build();

  }

  private AddressEntity mapAddress(UserDto userDto) {
    return AddressEntity.builder()//
        .street(userDto.getAddress().getStreet())//
        .suite(userDto.getAddress().getSuite())//
        .city(userDto.getAddress().getCity())//
        .zipcode(userDto.getAddress().getZipcode())//
        .lat(userDto.getAddress().getGeo().getLat())//
        .lng(userDto.getAddress().getGeo().getLng())//
        .build();

  }

  private CompanyEntity mapCompany(UserDto userDto) {
    return CompanyEntity.builder()//
        .name(userDto.getCompany().getName())//
        .catchPhrase(userDto.getCompany().getCatchPhrase())//
        .bs(userDto.getCompany().getBs())//
        .build();

  }



}
