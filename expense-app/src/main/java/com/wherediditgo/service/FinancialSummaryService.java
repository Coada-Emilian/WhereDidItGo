
package com.wherediditgo.service;

import java.math.BigDecimal;

import com.wherediditgo.repository.AccountRepository;
import com.wherediditgo.repository.ExpenseRepository;
import com.wherediditgo.repository.IncomeRepository;

/**
 * Calculates financial totals and balances from accounts, income records,
 * and expense records. Totals are calculated when requested rather than stored.
 */
public class FinancialSummaryService {
  private final AccountRepository accountRepository;
  private final ExpenseRepository expenseRepository;
  private final IncomeRepository incomeRepository;

  public FinancialSummaryService(
      AccountRepository accountRepository,
      ExpenseRepository expenseRepository,
      IncomeRepository incomeRepository) {
    this.accountRepository = accountRepository;
    this.expenseRepository = expenseRepository;
    this.incomeRepository = incomeRepository;
  }

  /**
   * Calculates the sum of all income records.
   */
  public BigDecimal getTotalIncome() {
    // TODO: Implement
    return null;
  }

  /**
   * Calculates the sum of all expense records.
   */
  public BigDecimal getTotalExpenses() {
    // TODO: Implement
    return null;
  }

  /**
   * Calculates net cash flow: total income minus total expenses.
   */
  public BigDecimal getNetCashFlow() {
    // TODO: Implement
    return null;
  }

  /**
   * Calculates an account's balance from its opening balance
   * and its associated income and expenses.
   */
  public BigDecimal getAccountBalance(Long accountId) {
    // TODO: Implement
    return null;
  }

  /**
   * Calculates the combined balance across all accounts.
   */
  public BigDecimal getTotalBalance() {
    // TODO: Implement
    return null;
  }
}
