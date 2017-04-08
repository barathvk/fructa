package com.xing.coding.challenge.api;

import com.xing.coding.challenge.Application;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootService {
  @RequestMapping
  public String greet() {
    return "Welcome to Fructa";
  }
}
