package com.xing.coding.challenge.api.account;

import com.xing.coding.challenge.Application;
import com.xing.coding.challenge.metrics.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class TransactionService {
  @Autowired
  private TransactionRepository repository;
  @Autowired
  private Metrics metrics;
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
    bal = getBalance(userId);
    repository.save(new Trans(userId, 10));
    repository.save(new Trans("bank", -10));
    metrics.transactionCount.inc();
    metrics.bankBalance.set(getBalance("bank"));
    metrics.houseBalance.set(getBalance("house"));
    metrics.userBalance.labels(userId).set(bal);
    try {
      metrics.send();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return bal;
  }
  public TransactionService(TransactionRepository repository) {
    this.repository = repository;
  }
}
