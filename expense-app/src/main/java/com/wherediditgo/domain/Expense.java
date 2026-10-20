package com.wherediditgo.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
  private Long id;
  private BigDecimal amount;
  private LocalDate date;
  private String description;
  private Category category;
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
   * @return Category return the category
   */
  public Category getCategory() {
    return category;
  }

  /**
   * @return Account return the account
   */
  public Account getAccount() {
    return account;
  }

  // Constructor for creating a new Expense with validation
  public Expense(BigDecimal amount, LocalDate date, String description, Category category, Account account) {
    if (amount == null || amount.signum() <= 0) {
      throw new IllegalArgumentException("Expense amount must be positive");
    }

    if (date == null) {
      throw new IllegalArgumentException("Expense date cannot be null");
    }

    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Expense description cannot be blank");
    }

    if (category == null) {
      throw new IllegalArgumentException("Expense category cannot be null");
    }

    if (account == null) {
      throw new IllegalArgumentException("Expense account cannot be null");
    }

    this.amount = amount;
    this.date = date;
    this.description = description;
    this.category = category;
    this.account = account;
  }

  // Method to assign an ID to the expense with validation
  public void assignId(Long id) {
    if (this.id != null) {
      throw new IllegalStateException("Expense already has an ID");
    }

    if (id == null || id <= 0) {
      throw new IllegalArgumentException("ID must be positive");
    }

    this.id = id;
  }
}