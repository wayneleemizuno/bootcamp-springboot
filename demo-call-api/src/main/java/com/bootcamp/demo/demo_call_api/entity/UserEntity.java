package com.bootcamp.demo.demo_call_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "forum_user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long forumUserId;
  private String name;
  private String username;
  private String email;
  private String phone;
  private String website;

  // @OneToOne(mappedBy = "userEntity", cascade = CascadeType.PERSIST)
  // @JsonManagedReference
  // private AddressEntity address;

  // @OneToOne(mappedBy = "userEntity", cascade = CascadeType.PERSIST)
  // @JsonManagedReference
  // private CompanyEntity company;

}
