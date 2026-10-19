package com.wherediditgo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wherediditgo.domain.Account;
import com.wherediditgo.domain.AccountType;

public class AccountRepositoryTest {

  private AccountRepository accountRepository;

  @BeforeEach
  void setUp() {
    accountRepository = new AccountRepository();
  }

  @Test
  public void createAccount_shouldSaveAccount() {
    Account account = new Account("Main account", AccountType.CHECKING);

    accountRepository.create(account);

    assertEquals(1, accountRepository.findAll().size());
    assertEquals(account, accountRepository.findAll().get(0));
  }
}