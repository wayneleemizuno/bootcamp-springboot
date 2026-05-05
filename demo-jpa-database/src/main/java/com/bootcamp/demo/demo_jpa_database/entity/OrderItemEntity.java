package com.bootcamp.demo.demo_jpa_database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderItemEntity {
  @Id // PK
  @GeneratedValue(strategy = GenerationType.IDENTITY) // serial, auto_increment
  private Long id;
  @Column(nullable = false)
  private Double price;
  @Column(nullable = false)
  private Integer quantity;
  @Column(nullable = false)
  @Setter
  private Double subTotal;

  // FK
  @ManyToOne
  @JoinColumn(name = "order_id")
  @Setter
  private OrderEntity orderEntity;
}
