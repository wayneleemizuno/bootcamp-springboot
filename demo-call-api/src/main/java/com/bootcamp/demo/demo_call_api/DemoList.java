package com.bootcamp.demo.demo_call_api;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class DemoList {
  public static void main(String[] args) {
    List<Apple> apples =
        new ArrayList<>(List.of(new Apple("a1", 2.3), new Apple("a2", 2.3), new Apple("a3", 2.3)));
    List<Banana> bananas = new ArrayList<>();
    for (Apple apple : apples) {
      Banana banana = Banana.builder().price(apple.getPrice()).build();
      bananas.add(banana);
    }
    System.out.println(bananas);
  }

  @AllArgsConstructor
  @Getter
  public static class Apple {
    private String name;
    private double price;
  }

  @AllArgsConstructor
  @Builder
  @ToString
  public static class Banana {
    private double price;
  }
}
