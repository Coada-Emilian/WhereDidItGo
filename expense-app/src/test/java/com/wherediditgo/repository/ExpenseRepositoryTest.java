package com.wherediditgo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wherediditgo.domain.Account;
import com.wherediditgo.domain.AccountType;
import com.wherediditgo.domain.Category;
import com.wherediditgo.domain.Expense;

public class ExpenseRepositoryTest {
  private ExpenseRepository repository;

  @BeforeEach
  void setUp() {
    repository = new ExpenseRepository();
  }

  @Test
  public void create_shouldStoreExpense() {

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

  @Test
  public void create_shouldRejectNullExpense() {
    assertThrows(
        IllegalArgumentException.class,
        () -> repository.create(null));
  }

  @Test
  public void create_shouldAssignUniqueIds() {
    Account account = new Account("Main account", AccountType.CHECKING);
    Category category = new Category("Groceries");

    Expense expense1 = new Expense(
        new BigDecimal("25.50"),
        LocalDate.of(2026, 10, 12),
        "Weekly shopping",
        category,
        account);

    Expense expense2 = new Expense(
        new BigDecimal("30.00"),
        LocalDate.of(2026, 10, 13),
        "Extra shopping",
        category,
        account);

    repository.create(expense1);
    repository.create(expense2);

    assertEquals(true, expense1.getId() != null);
    assertEquals(true, expense2.getId() != null);
    assertEquals(true, !expense1.getId().equals(expense2.getId()));
  }

  @Test
  public void findAll_shouldReturnAllExpenses() {

    Account account1 = new Account("Main account", AccountType.CHECKING);
    Category category1 = new Category("Groceries");

    Expense expense1 = new Expense(
        new BigDecimal("25.50"),
        LocalDate.of(2026, 10, 12),
        "Weekly shopping",
        category1,
        account1);

    Account account2 = new Account("Savings account", AccountType.SAVINGS);
    Category category2 = new Category("Entertainment");

    Expense expense2 = new Expense(
        new BigDecimal("15.00"),
        LocalDate.of(2026, 10, 13),
        "Movie ticket",
        category2,
        account2);

    repository.create(expense1);
    repository.create(expense2);

    var expenses = repository.findAll();

    assertEquals(2, expenses.size());
    assertEquals(expense1, expenses.get(0));
    assertEquals(expense2, expenses.get(1));
  }

  @Test
  public void findAll_shouldReturnEmptyListWhenNoExpenses() {
    var expenses = repository.findAll();

    assertEquals(0, expenses.size());
  }

  @Test
  public void findById_shouldReturnExpenseWhenExists() {
    Account account = new Account("Savings account", AccountType.SAVINGS);
    Category category = new Category("Strippers & BlackJack");

    Expense expense = new Expense(
        new BigDecimal("100.00"),
        LocalDate.of(2026, 10, 14),
        "Weekend fun",
        category,
        account);

    repository.create(expense);

    var foundExpense = repository.findById(expense.getId());

    assertEquals(expense, foundExpense);
  }

  @Test
  public void findById_shouldNotReturnExpenseIfNoneExists() {
    var expense = repository.findById(1L);

    assertEquals(null, expense);
  }

  @Test
  public void findById_shouldRejectNullId() {
    assertThrows(
        IllegalArgumentException.class,
        () -> repository.findById(null));
  }

  @Test
  public void delete_shouldDeleteExpense() {
    Account account = new Account("Savings account", AccountType.SAVINGS);
    Category category = new Category("Strippers & BlackJack");

    Expense expense = new Expense(
        new BigDecimal("100.00"),
        LocalDate.of(2026, 10, 14),
        "Weekend fun",
        category,
        account);

    repository.create(expense);

    assertEquals(1, repository.findAll().size());

    Expense foundExpense = repository.findAll().get(0);

    repository.delete(foundExpense);

    assertEquals(0, repository.findAll().size());
  }

  @Test
  public void delete_shouldRejectNullExpense() {
    assertThrows(
        IllegalArgumentException.class,
        () -> repository.delete(null));
  }
}
