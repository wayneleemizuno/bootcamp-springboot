package com.bootcamp.demo.demo_call_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String street;
  private String suite;
  private String city;
  private String zipcode;
  private Double latitude;
  private Double longitude;

  @OneToOne
  @JoinColumn(name = "user_id")
  @Setter
  // @JsonBackReference
  private UserEntity userEntity;
}
