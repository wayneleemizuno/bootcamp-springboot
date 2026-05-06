package com.bootcamp.demo.demo_jpa_database.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.demo.demo_jpa_database.entity.CartItemEntity;
import com.bootcamp.demo.demo_jpa_database.entity.OrderEntity;
import com.bootcamp.demo.demo_jpa_database.entity.OrderItemEntity;
import com.bootcamp.demo.demo_jpa_database.repository.CartItemRepository;
import com.bootcamp.demo.demo_jpa_database.repository.OrderItemRepository;
import com.bootcamp.demo.demo_jpa_database.repository.OrderRepository;

@Service
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private OrderItemRepository orderItemRepository;
  @Autowired
  private CartItemRepository cartItemRepository;

  public OrderEntity checkout() {
    // payment step
    List<CartItemEntity> cartItems = cartItemRepository.findAll();
    List<OrderItemEntity> orderItems = cartItems.stream().map(cartItem -> {
      OrderItemEntity orderItem = OrderItemEntity.builder().price(cartItem.getPrice()).quantity(cartItem.getQuantity())
          .build();
      return orderItem;
    }).collect(Collectors.toList());

    Double orderAmount = orderItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    OrderEntity orderEntity = OrderEntity.builder().amount(orderAmount).orderTime(LocalDateTime.now()).build();
    for (OrderItemEntity orderItem : orderItems) {
      orderItem.setOrderEntity(orderEntity);
      orderItem.setSubTotal(orderItem.getPrice() * orderItem.getQuantity());
    }
    this.orderRepository.save(orderEntity);// one insert
    this.orderItemRepository.saveAll(orderItems);// many insert

    this.cartItemRepository.deleteAll();
    return orderEntity;

  }
}
