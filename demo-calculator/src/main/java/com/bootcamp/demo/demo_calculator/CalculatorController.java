package com.bootcamp.demo.demo_calculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class CalculatorController {
  // http://localhost:8080/add?num1=3&num2=7
  @GetMapping(value = "/add")
  public String add(@RequestParam Integer num1, @RequestParam Integer num2) {
    return "result = " + (num1 + num2);
  }

  // http://localhost:8080/add/num1/2/num2/3
  @GetMapping(value = "/add/{num1}+{num2}")
  public String add2(@PathVariable Integer num1, @PathVariable Integer num2) {
    return "result = " + (num1 + num2);
  }

  @GetMapping(value = "/sub/{x}-{y}")
  public String sub(@PathVariable(value = "x") Integer num1, @PathVariable(value = "y") Integer num2) {
    return "result = " + (num1 - num2);
  }

  @GetMapping(value = "/mul/{num1}x{num2}")
  public String mul(@PathVariable Integer num1, @PathVariable Integer num2) {
    return "result = " + (num1 * num2);
  }

  @GetMapping(value = "/div/{num1}/{num2}")
  public String div(@PathVariable String num1, @PathVariable String num2) {
    try {
      return "result=" + Integer.valueOf(num1) / Integer.valueOf(num2);
    } catch (NumberFormatException e) {
      return "invalid value.";
    }
  }

}
