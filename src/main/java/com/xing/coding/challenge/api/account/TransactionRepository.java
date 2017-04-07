package com.xing.coding.challenge.api.account;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Trans, String> {
  List<Trans> findByUserId(String userId);
}
