package com.xing.coding.challenge.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
  @Autowired
  private AdminService service;
  @RequestMapping("/balance")
  int getBalance() {
    return service.getBalance();
  }
}
