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
   * @param amount the amount to set
   */
  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  /**
   * @return LocalDate return the date
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * @param date the date to set
   */
  public void setDate(LocalDate date) {
    this.date = date;
  }

  /**
   * @return String return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return Category return the category
   */
  public Category getCategory() {
    return category;
  }

  /**
   * @param category the category to set
   */
  public void setCategory(Category category) {
    this.category = category;
  }

  /**
   * @return Account return the account
   */
  public Account getAccount() {
    return account;
  }

  /**
   * @param account the account to set
   */
  public void setAccount(Account account) {
    this.account = account;
  }

  public Expense(BigDecimal amount, LocalDate date, String description, Category category, Account account) {
    this.amount = amount;
    this.date = date;
    this.description = description;
    this.category = category;
    this.account = account;
  }
}