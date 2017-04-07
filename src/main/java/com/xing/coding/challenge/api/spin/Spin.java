package com.xing.coding.challenge.api.spin;

import com.xing.coding.challenge.api.account.Trans;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
class Spin {
  @ElementCollection(targetClass = Fruit.class)
  @Enumerated(EnumType.STRING)
  private List<Fruit> result;
  private String userId;
  private Boolean win;
  public List<Fruit> getResult() {
    return result;
  }

  public String getUserId() {
    return userId;
  }

  public UUID getId() {
    return id;
  }

  public Trans getTransIn() {
    return transIn;
  }

  public Trans getTransOut() {
    return transOut;
  }
  public int getBalance() {
    return balance;
  }
  public Boolean getWin() {
    return win;
  }
  @Transient
  private int balance;
  @Id
  private UUID id;
  @OneToOne
  private Trans transIn;
  @OneToOne
  private Trans transOut;

  Spin() {}
  Spin(List<Fruit> result, String user, Boolean win, Trans transIn, Trans transOut, int balance) {
    this.id = UUID.randomUUID();
    this.result = result;
    this.userId = user;
    this.transIn = transIn;
    this.transOut = transOut;
    this.win = win;
    this.balance = balance;
  }
}
