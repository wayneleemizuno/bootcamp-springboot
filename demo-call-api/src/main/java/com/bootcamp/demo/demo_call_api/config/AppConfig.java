package com.bootcamp.demo.demo_call_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// * Configuration
// ! for built-in Java Class (Bean)
@Configuration
public class AppConfig {
  @Bean
  RestTemplate restTemplate() {
    // you can configure your own RestTemplate Object
    return new RestTemplate(); // stateful object (connection pool)
  }
}
