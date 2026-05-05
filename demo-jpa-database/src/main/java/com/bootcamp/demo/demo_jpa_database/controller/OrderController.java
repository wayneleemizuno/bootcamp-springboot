package com.bootcamp.demo.demo_jpa_database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootcamp.demo.demo_jpa_database.entity.OrderEntity;
import com.bootcamp.demo.demo_jpa_database.entity.OrderItemEntity;
import com.bootcamp.demo.demo_jpa_database.service.OrderService;

// ! Controller (transform data) -> Service (business logic) -> Repository (CRUD)

@Controller
@ResponseBody
public class OrderController {
  @Autowired
  private OrderService orderService;

  // PostMapping
  @PostMapping(value = "/checkout")
  public OrderEntity createOrder(@RequestBody List<OrderItemEntity> items) {
    for (OrderItemEntity entity : items) {
      if (entity.getPrice() == null || entity.getQuantity() == null) {
        throw new IllegalArgumentException("Null Value");
      }
      if (entity.getPrice() < 0 || entity.getQuantity() < 0) {
        throw new IllegalArgumentException("Negative Value");
      }
    }
    return this.orderService.checkout(items);
  }
}
