
package com.wherediditgo.service;

import java.util.List;

import com.wherediditgo.domain.Account;
import com.wherediditgo.repository.AccountRepository;

/**
 * Manages accounts and delegates account operations to AccountRepository.
 */
public class AccountService {
  private final AccountRepository repository;

  public AccountService(AccountRepository repository) {
    this.repository = repository;
  }

  /**
   * Creates an account with its name, type, and opening balance.
   */
  public void createAccount(Account account) {
    // TODO: Implement
  }

  /**
   * Retrieves all accounts.
   */
  public List<Account> getAllAccounts() {
    // TODO: Implement
    return null;
  }

  /**
   * Retrieves an account by ID.
   * Throws IllegalArgumentException if the ID is null or the account doesn't
   * exist.
   */
  public Account getAccountById(Long id) {
    // TODO: Implement
    return null;
  }

  /**
   * Updates an existing account.
   * Throws IllegalArgumentException if the ID is invalid or the account is null.
   */
  public void updateAccount(Long id, Account account) {
    // TODO: Implement
  }

  /**
   * Deletes an account by ID.
   * Throws IllegalArgumentException if the ID is null or the account doesn't
   * exist.
   */
  public void deleteAccount(Long id) {
    // TODO: Implement
  }
}
