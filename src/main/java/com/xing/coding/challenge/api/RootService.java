package com.xing.coding.challenge.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RootService {
  @RequestMapping
  public String greet() {
    return "Welcome to Fructa";
  }
}
