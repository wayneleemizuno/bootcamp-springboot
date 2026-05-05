package com.bootcamp.demo.demo_jpa_database.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

  public OrderEntity checkout(List<OrderItemEntity> items) {
    // payment step
    // clear cart
    Double orderAmount =
        items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    OrderEntity orderEntity =
        OrderEntity.builder().amount(orderAmount).orderTime(LocalDateTime.now()).build();
    for (OrderItemEntity orderItem : items) {
      orderItem.setOrderEntity(orderEntity);
      orderItem.setSubTotal(orderItem.getPrice() * orderItem.getQuantity());
    }
    this.orderRepository.save(orderEntity);// one insert
    this.orderItemRepository.saveAll(items);// many insert

    this.cartItemRepository.deleteAll();
    return orderEntity;

  }
}
