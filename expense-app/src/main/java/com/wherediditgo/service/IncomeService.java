
package com.wherediditgo.service;

import java.util.List;

import com.wherediditgo.domain.Income;
import com.wherediditgo.repository.IncomeRepository;

/**
 * Manages income records and delegates storage operations to IncomeRepository.
 */
public class IncomeService {
  private final IncomeRepository repository;

  public IncomeService(IncomeRepository repository) {
    this.repository = repository;
  }

  /**
   * Creates a new income record.
   */
  public void createIncome(Income income) {
    // TODO: Implement
  }

  /**
   * Retrieves all income records.
   */
  public List<Income> getAllIncomes() {
    // TODO: Implement
    return null;
  }

  /**
   * Retrieves an income record by ID.
   * Throws IllegalArgumentException if the ID is null or the income doesn't
   * exist.
   */
  public Income getIncomeById(Long id) {
    // TODO: Implement
    return null;
  }

  /**
   * Updates an existing income record.
   * Throws IllegalArgumentException if the ID is invalid or the income is null.
   */
  public void updateIncome(Long id, Income income) {
    // TODO: Implement
  }

  /**
   * Deletes an income record by ID.
   * Throws IllegalArgumentException if the ID is null or the income doesn't
   * exist.
   */
  public void deleteIncome(Long id) {
    // TODO: Implement
  }
}
