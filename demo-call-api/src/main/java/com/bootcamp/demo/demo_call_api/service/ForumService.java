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

  public List<UserDto> getUser() {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/users";

    // * Deserialisation
    // in JSON([]), List = Array
    UserDto[] userDtos = restTemplate.getForObject(url, UserDto[].class); // =RESTful GET
    return Arrays.asList(userDtos);
  }

  public List<UserEntity> postUsers() {
    return this.getUser().stream().map(u -> {
      UserEntity userEntry = UserEntity.builder().forumUserId(u.getId()).name(u.getName()).username(u.getUsername())
          .email(u.getEmail()).phone(u.getPhone()).website(u.getWebsite()).build();
      AddressEntity addressEntry = AddressEntity.builder().street(u.getAddress().getStreet())
          .suite(u.getAddress().getSuite()).city(u.getAddress().getCity()).zipcode(u.getAddress().getZipcode())
          .lat(u.getAddress().getGeo().getLat()).lng(u.getAddress().getGeo().getLng()).build();
      addressEntry.setUserEntity(userEntry);
      CompanyEntity companyEntry = CompanyEntity.builder().name(u.getCompany().getName())
          .catchPhrase(u.getCompany().getCatchPhrase()).bs(u.getCompany().getBs()).build();
      companyEntry.setUserEntity(userEntry);
      this.userRepository.save(userEntry);
      this.addressRepository.save(addressEntry);
      this.companyRepository.save(companyEntry);
      return userEntry;
    }).collect(Collectors.toList());
  }

  public List<UserEntity> getDbUsers() {
    return this.userRepository.findAll();
  }

}
