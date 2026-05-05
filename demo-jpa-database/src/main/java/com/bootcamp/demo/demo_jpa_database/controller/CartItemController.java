package com.bootcamp.demo.demo_jpa_database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootcamp.demo.demo_jpa_database.entity.CartItemEntity;
import com.bootcamp.demo.demo_jpa_database.service.CartService;

@Controller
@ResponseBody
public class CartItemController {
  @Autowired
  private CartService cartService;

  @PostMapping(value = "/cartItem")
  public CartItemEntity create(@RequestBody CartItemEntity cartItemEntity) {
    return this.cartService.place(cartItemEntity);
  }

  @GetMapping(value = "/cartItems")
  public List<CartItemEntity> getAll() {
    return this.cartService.getAll();
  }

  @DeleteMapping(value = "/removedItem")
  public void remove(@RequestParam Long id) {
    this.cartService.remove(id);
  }

  @DeleteMapping(value = "/removedItems")
  public void removeAll() {
    this.cartService.removeAll();
  }
}
