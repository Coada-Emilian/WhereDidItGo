package com.wherediditgo.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Income {
  private Long id;
  private BigDecimal amount;
  private LocalDate date;
  private String description;
  private IncomeSource incomeSource;
  private Account account;

  /**
   * @return Long return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @return BigDecimal return the amount
   */
  public BigDecimal getAmount() {
    return amount;
  }

  /**
   * @return LocalDate return the date
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * @return String return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @return String return the source
   */
  public IncomeSource getIncomeSource() {
    return incomeSource;
  }

  /**
   * @return Account return the account
   */
  public Account getAccount() {
    return account;
  }

  public Income(BigDecimal amount, LocalDate date, String description, IncomeSource incomeSource, Account account) {
    if (amount == null || amount.signum() <= 0) {
      throw new IllegalArgumentException("Income amount must be positive");
    }

    if (date == null) {
      throw new IllegalArgumentException("Income date cannot be null");
    }

    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Income description cannot be blank");
    }

    if (incomeSource == null) {
      throw new IllegalArgumentException("Income source cannot be null");
    }

    if (account == null) {
      throw new IllegalArgumentException("Income account cannot be null");
    }

    this.amount = amount;
    this.date = date;
    this.description = description;
    this.incomeSource = incomeSource;
    this.account = account;
  }
}