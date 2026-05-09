package com.bootcamp.demo.bc_forum.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserDetailDto {
  private Long id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;
  private UserPostDto[] posts;

  @Getter
  @Builder
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Getter
    @Builder
    public static class Geo {
      private Double lat;
      private Double lng;
    }
  }

  @Getter
  @Builder
  public static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }
}
