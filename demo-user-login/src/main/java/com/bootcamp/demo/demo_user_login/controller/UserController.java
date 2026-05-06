package com.bootcamp.demo.demo_user_login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootcamp.demo.demo_user_login.dto.LoginResponse;
import com.bootcamp.demo.demo_user_login.dto.RegisterRequest;
import com.bootcamp.demo.demo_user_login.dto.RegisterResponse;
import com.bootcamp.demo.demo_user_login.entity.UserEntity;
import com.bootcamp.demo.demo_user_login.exception.BusinessException;
import com.bootcamp.demo.demo_user_login.service.UserService;

@Controller
@ResponseBody
public class UserController {
  @Autowired
  UserService userService;

  @GetMapping(value = "/user")
  public UserEntity getUser(@RequestParam String username) {
    return this.userService.getUser(username);
  }

  @PostMapping(value = "/register")
  public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
    try {
      this.userService.registration(registerRequest);
      return RegisterResponse.builder().isSuccessful(true).message("Registration Successful.").build();
    } catch (NullPointerException e) {
      return RegisterResponse.builder().isSuccessful(false).message(e.getMessage()).build();
    } catch (BusinessException b) {
      return RegisterResponse.builder().isSuccessful(false).message(b.getMessage()).build();
    }
  }

  // ! @RequestBody -> for PostMapping & PutMapping only

  @GetMapping(value = "/login")
  // @RequestParam / @PathVariable -> for all RESTful
  public LoginResponse login(@RequestParam String username, @RequestParam String password) {
    return this.userService.login(username, password);
  }
}
