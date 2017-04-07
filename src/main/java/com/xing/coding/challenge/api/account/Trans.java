package com.xing.coding.challenge.api.account;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Trans {

  @Id
  private UUID id;
  private int amount;
  private String userId;
  public Trans(){}
  public Trans(String userId, int amount) {
    this.id = UUID.randomUUID();
    this.amount = amount;
    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}
