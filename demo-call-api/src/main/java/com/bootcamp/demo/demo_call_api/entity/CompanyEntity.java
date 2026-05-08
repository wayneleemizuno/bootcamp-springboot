package com.bootcamp.demo.demo_call_api.entity;

import jakarta.persistence.Column;
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
@Table(name = "company")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100) // default 255
  private String name;

  @Column(name = "catch_phrase", length = 230)
  private String catchPhrase;

  @Column(length = 230)
  private String bs;

  @OneToOne
  @JoinColumn(name = "user_id")
  @Setter
  // @JsonBackReference
  private UserEntity userEntity;
}
