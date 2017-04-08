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
  public TransactionService(TransactionRepository repository) {
    this.repository = repository;
  }
}
