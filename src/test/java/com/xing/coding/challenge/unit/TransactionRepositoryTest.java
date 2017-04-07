package com.xing.coding.challenge.unit;

import com.xing.coding.challenge.api.account.Trans;
import com.xing.coding.challenge.api.account.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DataJpaTest
public class TransactionRepositoryTest {
  private String user = "player";
  @Autowired
  private TestEntityManager entityManager;
  @Autowired
  private TransactionRepository repository;
  private int getBalance() {
    return repository.findByUserId(user).stream().mapToInt(Trans::getAmount).sum();
  }
  @Test
  public void initialBalanceTest() {
    int bal = getBalance();
    assertThat(bal).isEqualTo(0);
  }
  @Test
  public void addTest() {
    int bal = getBalance();
    assertThat(bal).isEqualTo(0);
    Trans newtrans = new Trans(user, 2);
    repository.save(newtrans);
    bal = getBalance();
    assertThat(bal).isEqualTo(2);
  }
  @Test
  public void removeTest() {
    int bal = getBalance();
    assertThat(bal).isEqualTo(0);
    Trans newtrans = new Trans(user, 2);
    repository.save(newtrans);
    bal = getBalance();
    assertThat(bal).isEqualTo(2);
    Trans remtrans = new Trans(user, -1);
    repository.save(remtrans);
    bal = getBalance();
    assertThat(bal).isEqualTo(1);
  }
}
