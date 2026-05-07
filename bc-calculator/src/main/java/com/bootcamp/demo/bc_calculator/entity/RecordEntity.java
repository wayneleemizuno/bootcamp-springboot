package com.bootcamp.demo.bc_calculator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "calculation_record")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RecordEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String x;
  private String y;
  private String operation;
  @Setter
  private String result;
  @Setter
  private String code;
  @Setter
  private String message;
}
