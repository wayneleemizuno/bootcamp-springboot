package com.bootcamp.demo.demo_jpa_database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootcamp.demo.demo_jpa_database.entity.OrderEntity;
import com.bootcamp.demo.demo_jpa_database.service.OrderService;

// ! Controller (transform data) -> Service (business logic) -> Repository (CRUD)

@Controller
@ResponseBody
public class OrderController {
  @Autowired
  private OrderService orderService;

  // PostMapping
  @PostMapping(value = "/checkout")
  public OrderEntity createOrder() {
    return this.orderService.checkout();
  }
}
