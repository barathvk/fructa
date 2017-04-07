package com.xing.coding.challenge.api.admin;

import com.xing.coding.challenge.api.account.Trans;
import com.xing.coding.challenge.api.account.TransactionRepository;
import com.xing.coding.challenge.api.account.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
class AdminService {
  @Autowired
  private TransactionRepository repository;
  int getBalance() {
    return StreamSupport.stream(repository.findAll().spliterator(), false).mapToInt(t -> t.getAmount() * -1).sum();
  }
}
