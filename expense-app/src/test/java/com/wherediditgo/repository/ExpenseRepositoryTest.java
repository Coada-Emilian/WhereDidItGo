package com.wherediditgo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.wherediditgo.domain.Account;
import com.wherediditgo.domain.AccountType;
import com.wherediditgo.domain.Category;
import com.wherediditgo.domain.Expense;

public class ExpenseRepositoryTest {

  @Test
  public void create_shouldStoreExpense() {
    ExpenseRepository repository = new ExpenseRepository();

    Account account = new Account("Main account", AccountType.CHECKING);
    Category category = new Category("Groceries");

    Expense expense = new Expense(
        new BigDecimal("25.50"),
        LocalDate.of(2026, 10, 12),
        "Weekly shopping",
        category,
        account);

    repository.create(expense);

    assertEquals(1, repository.findAll().size());
    assertEquals(expense, repository.findAll().get(0));
  }
}