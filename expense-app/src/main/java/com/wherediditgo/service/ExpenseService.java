
package com.wherediditgo.service;

import java.util.List;

import com.wherediditgo.domain.Expense;
import com.wherediditgo.repository.ExpenseRepository;

/**
 * Manages expenses and delegates storage operations to ExpenseRepository.
 */
public class ExpenseService {
  private final ExpenseRepository repository;

  public ExpenseService(ExpenseRepository repository) {
    this.repository = repository;
  }

  /**
   * Creates a new expense.
   */
  public void createExpense(Expense expense) {
    // TODO: Implement
  }

  /**
   * Retrieves all expenses.
   */
  public List<Expense> getAllExpenses() {
    // TODO: Implement
    return null;
  }

  /**
   * Retrieves an expense by ID.
   * Throws IllegalArgumentException if the ID is null or the expense doesn't
   * exist.
   */
  public Expense getExpenseById(Long id) {
    // TODO: Implement
    return null;
  }

  /**
   * Updates an existing expense.
   * Throws IllegalArgumentException if the ID is invalid or the expense is null.
   */
  public void updateExpense(Long id, Expense expense) {
    // TODO: Implement
  }

  /**
   * Deletes an expense by ID.
   * Throws IllegalArgumentException if the ID is null or the expense doesn't
   * exist.
   */
  public void deleteExpense(Long id) {
    // TODO: Implement
  }
}
