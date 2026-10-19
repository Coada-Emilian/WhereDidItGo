package com.wherediditgo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wherediditgo.domain.Account;
import com.wherediditgo.domain.AccountType;

public class AccountRepositoryTest {
  // Test class for AccountRepository
  private AccountRepository accountRepository;

  // Test setup method to initialize the repository before each test
  @BeforeEach
  void setUp() {
    accountRepository = new AccountRepository();
  }

  // Test method for creating an account
  @Test
  public void createAccount_shouldSaveAccount() {
    Account account = new Account("Main account", AccountType.CHECKING);

    accountRepository.create(account);

    assertEquals(1, accountRepository.findAll().size());
    assertEquals(account, accountRepository.findAll().get(0));
  }

  // Test method for rejecting null account creation
  @Test
  public void createAccount_shouldRejectNullAccount() {
    assertThrows(
        IllegalArgumentException.class,
        () -> accountRepository.create(null));

  }

  // Test method for assigning unique IDs to created accounts
  @Test
  public void createAccount_shouldAssignUniqueIds() {
    Account account1 = new Account("First account", AccountType.SAVINGS);
    Account account2 = new Account("Second account", AccountType.CHECKING);

    accountRepository.create(account1);
    accountRepository.create(account2);

    assertEquals(true, account1.getId() != null);
    assertEquals(true, account2.getId() != null);

    assertEquals(1L, account1.getId());
    assertEquals(2L, account2.getId());

    assertEquals(true, !account1.getId().equals(account2.getId()));
  }

  @Test
  // Test method for finding all accounts
  public void findAll_shouldReturnAllAccounts() {
    Account account1 = new Account("First account", AccountType.SAVINGS);
    Account account2 = new Account("Second account", AccountType.CHECKING);
    Account account3 = new Account("Third account", AccountType.INVESTMENT);

    accountRepository.create(account1);
    accountRepository.create(account2);
    accountRepository.create(account3);

    assertEquals(3, accountRepository.findAll().size());
    assertEquals(account1, accountRepository.findAll().get(0));
    assertEquals(account2, accountRepository.findAll().get(1));
    assertEquals(account3, accountRepository.findAll().get(2));
  }

  @Test
  // Test method for finding all accounts when none exist
  public void findAll_shouldReturnEmptyListWhenNoAccounts() {
    assertEquals(0, accountRepository.findAll().size());
  }

  @Test
  // Test method for finding an account by ID when it exists
  public void findById_shouldReturnAccountWhenExists() {
    Account account = new Account("Main account", AccountType.CHECKING);
    accountRepository.create(account);

    var foundAccount = accountRepository.findById(account.getId());

    assertEquals(account, foundAccount);
  }

  @Test
  // Test method for finding an account by ID when it does not exist
  public void findById_shouldReturnNullWhenAccountDoesNotExist() {
    var foundAccount = accountRepository.findById(1L);

    assertEquals(null, foundAccount);
  }

  @Test
  // Test method for rejecting null ID in findById
  public void findById_shouldRejectNullId() {
    assertThrows(
        IllegalArgumentException.class,
        () -> accountRepository.findById(null));
  }

  @Test
  // Test method for deleting an account
  public void delete_shouldRemoveAccount() {
    Account account = new Account("Main account", AccountType.CHECKING);
    accountRepository.create(account);

    assertEquals(1, accountRepository.findAll().size());

    Account foundAccount = accountRepository.findById(account.getId());

    accountRepository.delete(foundAccount);

    assertEquals(0, accountRepository.findAll().size());
  }

  @Test
  // Test method for rejecting null account deletion
  public void delete_shouldRejectNullAccount() {
    assertThrows(
        IllegalArgumentException.class,
        () -> accountRepository.delete(null));
  }

  @Test
  // Test method for rejecting deletion of a non-existent account
  public void delete_shouldRejectNonExistentAccount() {
    Account account = new Account("Non-existent account", AccountType.SAVINGS);
    assertThrows(
        IllegalArgumentException.class,
        () -> accountRepository.delete(account));
  }

  @Test
  // Test method for updating an account
  public void update_shouldUpdateAccount() {
    Account account = new Account("Main account", AccountType.CHECKING);
    accountRepository.create(account);

    Account updatedAccount = new Account("Updated account", AccountType.SAVINGS);

    updatedAccount.assignId(account.getId());
    accountRepository.update(updatedAccount);

    Account foundAccount = accountRepository.findById(account.getId());
    assertEquals(updatedAccount, foundAccount);
  }

  @Test
  // Test method for rejecting null account update
  public void update_shouldRejectNullAccount() {
    assertThrows(
        IllegalArgumentException.class,
        () -> accountRepository.update(null));
  }
}