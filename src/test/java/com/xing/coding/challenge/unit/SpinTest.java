package com.xing.coding.challenge.unit;

import com.xing.coding.challenge.api.account.TransactionService;
import com.xing.coding.challenge.api.spin.Spin;
import com.xing.coding.challenge.api.spin.SpinService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SpinTest {
  private Random r = new Random();
  @Autowired
  private SpinService spinservice;
  @Autowired
  private TransactionService transservice;
  @Test
  public void spinTest() throws Exception {
    simulateSpin();
  }
  @Test
  public void simulate10Spins() throws Exception {
    for (int i = 0; i < 10; i++) {
      simulateSpin();
    }
  }
  @Test
  public void simulate100Spins() throws Exception {
    for (int i = 0; i < 100; i++) {
      simulateSpin();
    }
  }
  @Test
  public void simulate1000Spins() throws Exception {
    for (int i = 0; i < 1000; i++) {
      simulateSpin();
    }
  }
  @Test
  public void simulate10000Spins() throws Exception {
    for (int i = 0; i < 10000; i++) {
      simulateSpin();
    }
  }
  private void simulateSpin() throws Exception {
    String user = "player" + (r.nextInt(3) + 1);
    int bal = transservice.getBalance(user);
    if (bal <= 0) {
      transservice.requestMore(user);
    }
    Spin spin = spinservice.spin(user);
    assertThat(spin.getUserId()).isNotNull();
    assertThat(spin.getTransIn()).isNotNull();
    if (spin.getWin()) {
      assertThat(spin.getTransOut()).isNotNull();
    }
    else {
      assertThat(spin.getTransOut()).isNull();
    }
    int adminbal = transservice.getBalance("admin");
    assertThat(adminbal).isGreaterThan(-100);
  }
}
