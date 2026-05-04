package com.bootcamp.demo.demo_helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HelloworldController {
  // ! API (Application Programming Interface)
  // http://localhost:8080/hello
  @GetMapping(value = "/hello")
  public String sayHello() {
    return "Hello World!";
  }

  // ! API (Application Programming Interface)
  // http://localhost:8080/goodbye
  @GetMapping(value = "/goodbye")
  public String sayGoodbye() {
    return "Goodbye!";
  }

  // * Parameter
  // * URL design
  // 1. Path Variable - http://localhost:8080/person/name/John/age/18
  @GetMapping(value = "/person/name/{name}/age/{age}")
  public String createPerson1(@PathVariable String name, @PathVariable Integer age) {
    System.out.println(name + "-" + age);
    return name + "-" + age;
  }

  // 2. Request Parameter - http://localhost:8080/person?name=john&age=18
  @GetMapping(value = "/person")
  public String createPerson2(@RequestParam String name, @RequestParam int age) {
    return name + "-" + age;
  }

  // * Maven Command
  // Windows/Mac -> ctrl + c -> exit spring boot app
  // mvn spring-boot:run -> run spring boot app
}
