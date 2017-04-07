package com.xing.coding.challenge.api.spin;

import com.xing.coding.challenge.api.account.Trans;
import com.xing.coding.challenge.api.account.TransactionRepository;
import com.xing.coding.challenge.api.account.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
@Service
class SpinService {
  @Value("${fructa.edge}")
  private double edge;
  @Autowired
  private TransactionRepository trepository;
  @Autowired
  private SpinRepository srepository;
  @Autowired
  private TransactionService tservice;
  Spin spin(String userId) throws Exception {
    Random r = new Random();
    Trans transIn = new Trans(userId, -1);
    Fruit[] fruits = new Fruit[Fruit.values().length];
    int balance = tservice.getBalance(userId);
    if (balance <= 0) {
      throw new Exception("Balance is 0");
    }
    for (int i = 0; i < Fruit.values().length; i++) {
      int chosenId = r.nextInt(Fruit.values().length);
      Fruit chosen = Fruit.values()[chosenId];
      fruits[i] = chosen;
    }
    Boolean win = Arrays.stream(fruits).allMatch(f -> f == fruits[0]);
    trepository.save(transIn);
    Trans transOut = null;
    double e = this.edge;
    if (win) {
      Double even = Math.pow(Fruit.values().length, Fruit.values().length - 1);
      int winamt = ((Double)Math.floor(even * (1 - edge))).intValue();
      transOut = new Trans(userId, winamt);
      trepository.save(transOut);
    }
    List<Fruit> result = Arrays.asList(fruits);
    balance = tservice.getBalance(userId);
    Spin spin = new Spin(result, userId, win, transIn, transOut, balance);
    srepository.save(spin);
    return spin;
  }
}
