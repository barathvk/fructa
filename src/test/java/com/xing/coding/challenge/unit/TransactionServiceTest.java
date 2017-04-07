package com.xing.coding.challenge.unit;

import com.xing.coding.challenge.api.account.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionServiceTest {
  private String user = "player";
  @Autowired
  private TransactionService service;
  @Test
  public void initialBalanceTest() {
    int bal = service.getBalance(user);
    assertThat(bal).isEqualTo(0);
  }
//  @Test
//  public void requestMoreTest() {
//    int bal = service.getBalance(user);
//    assertThat(bal).isEqualTo(0);
//    bal = service.requestMore(user);
//    assertThat(bal).isEqualTo(10);
//  }
}
