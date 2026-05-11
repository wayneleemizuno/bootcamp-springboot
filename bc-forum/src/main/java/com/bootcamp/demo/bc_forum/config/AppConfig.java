package com.bootcamp.demo.bc_forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  UserAPI userAPI() {
    return new UserAPI("https://jsonplaceholder.typicode.com/users");
  }

  @Bean
  PostAPI postAPI() {
    return new PostAPI("https://jsonplaceholder.typicode.com/posts");
  }

  @Bean
  CommentAPI commentAPI() {
    return new CommentAPI("https://jsonplaceholder.typicode.com/comments");
  }
}
