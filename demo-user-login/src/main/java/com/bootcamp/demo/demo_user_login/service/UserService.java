package com.bootcamp.demo.demo_user_login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.demo.demo_user_login.dto.LoginResponse;
import com.bootcamp.demo.demo_user_login.dto.RegisterRequest;
import com.bootcamp.demo.demo_user_login.entity.UserEntity;
import com.bootcamp.demo.demo_user_login.exception.BusinessException;
import com.bootcamp.demo.demo_user_login.exception.SystemError;
import com.bootcamp.demo.demo_user_login.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public void registration(RegisterRequest registerRequest) throws BusinessException {
    String userName = registerRequest.getUsername();
    String password = registerRequest.getPassword();
    String email = registerRequest.getEmail();
    if (userName == null || email == null || password == null)
      throw new NullPointerException("Values cannot be null");
    if (this.userRepository.existsByUsername(userName))
      throw new BusinessException(SystemError.USERNAME, "Username alreay exists.");
    if (this.userRepository.existsByEmail(email))
      throw new BusinessException(SystemError.EMAIL, "Email already exists.");
    if (userName.length() < 8)
      throw new BusinessException(SystemError.USERNAME, "Username must contain at least 8 characters.");
    if (password.length() < 8)
      throw new BusinessException(SystemError.PASSWORD, "Password must contain at least 8 characters.");
    if (!email.contains("@"))
      throw new BusinessException(SystemError.EMAIL, "Invalid email.");
    UserEntity newUser = UserEntity.builder().username(userName).email(email).password(password).build();
    this.userRepository.save(newUser);
  }

  public UserEntity getUser(String username) {
    Optional<UserEntity> oUser = this.userRepository.findByUsername(username);
    return oUser.isPresent() ? oUser.get() : null;
  }

  public LoginResponse login(String username, String password) {
    // ? @RequestParam doesn't get null, only empty string.
    // if (username == null || password == null) {
    // throw new NullPointerException("Values cannot be null.");
    // }
    String message = "Login Successful.";
    boolean isSucessful = true;
    Optional<UserEntity> oUser = this.userRepository.findByUsername(username);
    if (!oUser.isPresent()) {
      isSucessful = false;
      message = "Username doesn't exist.";
    } else {
      UserEntity user = oUser.get();
      if (!user.getPassword().equals(password)) {
        isSucessful = false;
        message = "Wrong password.";
      }
    }
    return LoginResponse.builder().isSuccessful(isSucessful).message(message).build();
  }

}
