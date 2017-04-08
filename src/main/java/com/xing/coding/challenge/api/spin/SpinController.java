package com.xing.coding.challenge.api.spin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spin")
public class SpinController {
  @Autowired
  private SpinService service;
  @RequestMapping
  public Spin spin() throws Exception {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String user = auth.getPrincipal().toString();
    return service.spin(user);
  }
}
