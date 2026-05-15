package com.bootcamp.demo.demo_call_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DemoCallApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoCallApiApplication.class, args);
  }
}
