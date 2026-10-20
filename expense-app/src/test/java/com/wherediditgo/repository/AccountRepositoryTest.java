package com.wherediditgo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.wherediditgo.domain.Account;
import com.wherediditgo.domain.AccountType;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class AccountRepositoryTest {

  private AccountRepository accountRepository;

  // Setup method to initialize the AccountRepository before each test
  @BeforeEach
  void setUp() {
    accountRepository = new AccountRepository();
  }

  // CREATE

  // Test for creating accounts
  @Test
  public void create_shouldSaveAccount() {
    Account account = new Account(
        "Main account",
        AccountType.CHECKING,
        new BigDecimal("100.00"));

    accountRepository.create(account);

    assertEquals(1, accountRepository.findAll().size());
    assertEquals(account, accountRepository.findAll().get(0));
  }

  // Test for rejecting null accounts
  @Test
  public void create_shouldRejectNullAccount() {
    assertThrows(
        IllegalArgumentException.class,
        () -> accountRepository.create(null));
  }

  // Test for assigning unique IDs to created accounts
  @Test
  public void create_shouldAssignUniqueIds() {
    Account account1 = new Account(
        "First account",
        AccountType.SAVINGS,
        new BigDecimal("200.00"));
    Account account2 = new Account(
        "Second account",
        AccountType.CHECKING,
        new BigDecimal("350.00"));

    accountRepository.create(account1);
    accountRepository.create(account2);

    assertNotNull(account1.getId());
    assertNotNull(account2.getId());
    assertEquals(1L, account1.getId());
    assertEquals(2L, account2.getId());
    assertNotEquals(account1.getId(), account2.getId());
  }

  // READ

  // Test for retrieving all accounts
  @Test
  public void findAll_shouldReturnAllAccounts() {
    Account account1 = new Account(
        "First account", AccountType.SAVINGS, new BigDecimal("200.00"));
    Account account2 = new Account(
        "Second account", AccountType.CHECKING, new BigDecimal("100.00"));
    Account account3 = new Account(
        "Third account", AccountType.INVESTMENT, new BigDecimal("300.00"));

    accountRepository.create(account1);
    accountRepository.create(account2);
    accountRepository.create(account3);

    assertEquals(3, accountRepository.findAll().size());
    assertEquals(account1, accountRepository.findAll().get(0));
    assertEquals(account2, accountRepository.findAll().get(1));
    assertEquals(account3, accountRepository.findAll().get(2));
  }

  // Test for retrieving all accounts when no accounts exist
  @Test
  public void findAll_shouldReturnEmptyListWhenNoAccounts() {
    assertEquals(0, accountRepository.findAll().size());
  }

  // Test for retrieving an account by ID when it exists
  @Test
  public void findById_shouldReturnAccountWhenExists() {
    Account account = new Account(
        "Main account", AccountType.CHECKING, new BigDecimal("100.00"));
    accountRepository.create(account);

    Account foundAccount = accountRepository.findById(account.getId());

    assertEquals(account, foundAccount);
  }

  // Test for retrieving an account by ID when it does not exist
  @Test
  public void findById_shouldReturnNullWhenAccountDoesNotExist() {
    assertNull(accountRepository.findById(1L));
  }

  // Test for rejecting null ID when retrieving an account by ID
  @Test
  public void findById_shouldRejectNullId() {
    assertThrows(
        IllegalArgumentException.class,
        () -> accountRepository.findById(null));
  }

  // UPDATE

  // Test for updating an account and preserving its ID
  @Test
  public void update_shouldUpdateAccountAndPreserveId() {
    Account account = new Account(
        "Main account", AccountType.CHECKING, new BigDecimal("100.00"));
    accountRepository.create(account);

    Long initialId = account.getId();

    Account updatedAccount = new Account(
        "Updated account",
        AccountType.SAVINGS,
        new BigDecimal("250.00"));

    accountRepository.update(initialId, updatedAccount);

    Account foundAccount = accountRepository.findById(initialId);

    assertEquals(initialId, foundAccount.getId());
    assertEquals("Updated account", foundAccount.getName());
    assertEquals(AccountType.SAVINGS, foundAccount.getAccountType());
    assertEquals(new BigDecimal("250.00"), foundAccount.getOpeningBalance());
    assertEquals(1, accountRepository.findAll().size());
  }

  // Test for rejecting null ID when updating an account
  @Test
  public void update_shouldRejectNullId() {
    Account updatedAccount = new Account(
        "Updated account", AccountType.SAVINGS, new BigDecimal("250.00"));

    assertThrows(
        IllegalArgumentException.class,
        () -> accountRepository.update(null, updatedAccount));
  }

  // Test for rejecting null account when updating an account
  @Test
  public void update_shouldRejectNullAccount() {
    assertThrows(
        IllegalArgumentException.class,
        () -> accountRepository.update(1L, null));
  }

  // Test for rejecting non-existent ID when updating an account
  @Test
  public void update_shouldRejectNonExistentId() {
    Account updatedAccount = new Account(
        "Updated account", AccountType.SAVINGS, new BigDecimal("250.00"));

    assertThrows(
        IllegalArgumentException.class,
        () -> accountRepository.update(99L, updatedAccount));
  }

  // DELETE

  // Test for deleting an account successfully
  @Test
  public void delete_shouldRemoveAccount() {
    Account account = new Account(
        "Main account", AccountType.CHECKING, new BigDecimal("400.00"));
    accountRepository.create(account);

    accountRepository.delete(account);

    assertEquals(0, accountRepository.findAll().size());
    assertNull(accountRepository.findById(account.getId()));
  }

  // Test for rejecting null account when deleting an account
  @Test
  public void delete_shouldRejectNullAccount() {
    assertThrows(
        IllegalArgumentException.class,
        () -> accountRepository.delete(null));
  }

  // Test for rejecting non-existent account when deleting an account
  @Test
  public void delete_shouldRejectNonExistentAccount() {
    Account account = new Account(
        "Non-existent account",
        AccountType.SAVINGS,
        new BigDecimal("100.00"));

    assertThrows(
        IllegalArgumentException.class,
        () -> accountRepository.delete(account));
  }
}