package com.xing.coding.challenge.metrics;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class Metrics {
  @Value("${fructa.gateway}")
  private String pgAddr;
  private PushGateway pg;
  private CollectorRegistry registry = new CollectorRegistry();
  public Counter transactionCount = Counter.build().name("transaction_count").help("Transaction count").register(registry);
  public Gauge bankBalance = Gauge.build().name("bank_balance").help("Bank balance").register(registry);
  public Gauge houseBalance = Gauge.build().name("house_balance").help("House balance").register(registry);
  public Gauge userBalance = Gauge.build().name("user_balance").help("User balance").labelNames("user").register(registry);
  public Counter spinCounter = Counter.build().name("spin_counter").help("Count of spins").labelNames("user", "win").register(registry);
  public void send() throws IOException {
    if (pg == null) {
      pg = new PushGateway(pgAddr);
    }
    pg.push(registry, "fructa");
  }
}
