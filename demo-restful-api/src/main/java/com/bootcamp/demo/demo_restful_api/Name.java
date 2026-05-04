package com.bootcamp.demo.demo_restful_api;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Name {
  private static long idCounter = 0L;

  // ! always use Long for id

  @Setter
  private Long id;
  private String firstName;
  private String lastName;

  public Name(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    id = ++idCounter;
  }
}
