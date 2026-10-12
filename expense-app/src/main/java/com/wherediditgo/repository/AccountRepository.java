package com.wherediditgo.repository;

import java.util.ArrayList;
import java.util.List;

import com.wherediditgo.domain.Account;

public class AccountRepository {

  private final List<Account> accounts = new ArrayList<>();

  private Long nextId = 1L;

  public void create(Account account) {
    if (account == null) {
      throw new IllegalArgumentException("Account cannot be null");
    }

    account.assignId(nextId);
    nextId++;
    accounts.add(income);
  }

  public List<Income> findAll() {
    return new ArrayList<>(incomes);
  }

  public Income findById(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("ID cannot be null");
    }

    return incomes.stream()
        .filter(income -> income.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  public void delete(Income income) {
    if (income == null) {
      throw new IllegalArgumentException("Income cannot be null");
    }

    incomes.remove(income);
  }

}