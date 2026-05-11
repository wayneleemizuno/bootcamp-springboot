package com.bootcamp.demo.demo_call_api.service.impl;

import com.bootcamp.demo.demo_call_api.dto.ForumUserResp;
import com.bootcamp.demo.demo_call_api.entity.AddressEntity;
import com.bootcamp.demo.demo_call_api.entity.CompanyEntity;
import com.bootcamp.demo.demo_call_api.entity.UserEntity;
import com.bootcamp.demo.demo_call_api.mapper.DtoMapper;
import com.bootcamp.demo.demo_call_api.mapper.EntityMapper;
import com.bootcamp.demo.demo_call_api.model.UserDto;
import com.bootcamp.demo.demo_call_api.repository.AddressRepository;
import com.bootcamp.demo.demo_call_api.repository.CompanyRepository;
import com.bootcamp.demo.demo_call_api.repository.UserRepository;
import com.bootcamp.demo.demo_call_api.service.ForumService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ForumServiceImpl implements ForumService {
  @Autowired UserRepository userRepository;
  @Autowired AddressRepository addressRepository;
  @Autowired CompanyRepository companyRepository;
  @Autowired DtoMapper dtoMapper;
  @Autowired EntityMapper entityMapper;
  // @Qualifier
  @Autowired RestTemplate restTemplate;

  public List<UserDto> getUsers() {
    String url = "https://jsonplaceholder.typicode.com/users";

    // * Deserialisation
    // in JSON([]), List = Array
    UserDto[] userDtos = this.restTemplate.getForObject(url, UserDto[].class); // =RESTful GET
    return Arrays.asList(userDtos);
  }

  public List<ForumUserResp> postUsers() {
    this.addressRepository.deleteAll();
    this.companyRepository.deleteAll();
    this.userRepository.deleteAll();

    List<UserEntity> users = new ArrayList<>();
    List<AddressEntity> addresses = new ArrayList<>();
    List<CompanyEntity> companies = new ArrayList<>();

    List<ForumUserResp> forumUserDtos =
        this.getUsers().stream()
            .map(
                u -> {
                  AddressEntity addressEntry = this.entityMapper.map(u.getAddress());
                  CompanyEntity companyEntry = this.entityMapper.map(u.getCompany());
                  UserEntity userEntry = this.entityMapper.map(u);
                  addressEntry.setUserEntity(userEntry);
                  companyEntry.setUserEntity(userEntry);
                  users.add(userEntry);
                  addresses.add(addressEntry);
                  companies.add(companyEntry);
                  return this.dtoMapper.map(u);
                })
            .collect(Collectors.toList());
    this.userRepository.saveAll(users);
    this.addressRepository.saveAll(addresses);
    this.companyRepository.saveAll(companies);
    return forumUserDtos;
  }

  public List<ForumUserResp> getDbUsers() {
    return this.userRepository.findAll().stream()
        .map(u -> this.dtoMapper.map(u))
        .collect(Collectors.toList());
  }
}
