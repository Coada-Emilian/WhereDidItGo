package com.wherediditgo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

  @Test
  public void createAccount_shouldRejectNullAccount() {
    assertThrows(
        IllegalArgumentException.class,
        () -> accountRepository.create(null));

  }

  @Test
  public void createAccount_shouldAssignUniqueIds() {
    Account account1 = new Account("First account", AccountType.SAVINGS);
    Account account2 = new Account("Second account", AccountType.CHECKING);

    accountRepository.create(account1);
    accountRepository.create(account2);

    assertEquals(1L, account1.getId());
    assertEquals(2L, account2.getId());

    assertEquals(true, account1.getId() != null);
    assertEquals(true, account2.getId() != null);
    assertEquals(true, !account1.getId().equals(account2.getId()));
  }
}