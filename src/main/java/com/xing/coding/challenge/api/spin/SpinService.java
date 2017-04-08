package com.xing.coding.challenge.api.spin;

import com.xing.coding.challenge.api.account.Trans;
import com.xing.coding.challenge.api.account.TransactionRepository;
import com.xing.coding.challenge.api.account.TransactionService;
import com.xing.coding.challenge.metrics.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
@Service
public class SpinService {
  @Value("${fructa.prize}")
  private int prize;
  @Autowired
  private TransactionRepository trepository;
  @Autowired
  private SpinRepository srepository;
  @Autowired
  private Metrics metrics;
  @Autowired
  private TransactionService tservice;
  public Spin spin(String userId) throws Exception {
    Random r = new Random();
    Fruit[] fruits = new Fruit[Fruit.values().length];
    for (int i = 0; i < Fruit.values().length; i++) {
      int chosenId = r.nextInt(Fruit.values().length);
      Fruit chosen = Fruit.values()[chosenId];
      fruits[i] = chosen;
    }
    return spin(userId, fruits);
  }
  private Spin spin(String userId, Fruit[] fruits) {
    Trans transIn = new Trans(userId, -1);
    Boolean win = Arrays.stream(fruits).allMatch(f -> f == fruits[0]);
    Trans transHouse = new Trans("house", 1);
    trepository.save(transIn);
    trepository.save(transHouse);
    Trans transOut = null;
    if (win) {
      transOut = new Trans(userId, prize);
      trepository.save(transOut);
      Trans transHouseOut = new Trans("house", -1 * prize);
      trepository.save(transHouseOut);
    }
    List<Fruit> result = Arrays.asList(fruits);
    int balance = tservice.getBalance(userId);
    Spin spin = new Spin(result, userId, win, transIn, transOut, balance);
    srepository.save(spin);
    metrics.transactionCount.inc();
    metrics.houseBalance.set(tservice.getBalance("house"));
    metrics.userBalance.labels(userId).set(balance);
    metrics.spinCounter.labels(userId, Boolean.toString(win)).inc();
    try {
      metrics.send();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return spin;
  }
}
