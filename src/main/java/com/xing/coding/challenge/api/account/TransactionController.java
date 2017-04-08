package com.xing.coding.challenge.api.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class TransactionController {
  @Autowired
  private TransactionService service;
  @RequestMapping("/transactions")
  public List<Trans> list(){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String user = auth.getPrincipal().toString();
    return service.list(user);
  }
  @RequestMapping("/balance")
  public int getBalance() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String user = auth.getPrincipal().toString();
    return service.getBalance(user);
  }
}
