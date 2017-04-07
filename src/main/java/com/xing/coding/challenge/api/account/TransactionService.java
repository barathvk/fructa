package com.xing.coding.challenge.api.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
  @Autowired
  private TransactionRepository repository;
  List<Trans> list(String userId) {
    return repository.findByUserId(userId);
  }
  public int getBalance(String userId) {
    List<Trans> trans = list(userId);
    return trans.stream().mapToInt(Trans::getAmount).sum();
  }
  public int requestMore(String userId) {
    int bal = getBalance(userId);
    if (bal > 0) {
      return bal;
    }
    repository.save(new Trans(userId, 10));
    return getBalance(userId);
  }
}
