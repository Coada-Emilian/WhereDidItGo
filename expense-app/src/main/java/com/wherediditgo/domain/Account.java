package com.wherediditgo.domain;

import java.math.BigDecimal;

public class Account {

    private Long id;
    private String name;
    private AccountType accountType;
    private BigDecimal openingBalance;

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return AccountType return the type
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * @return BigDecimal return the openingBalance
     */
    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    // Constructor for creating a new Account with validation
    public Account(String name, AccountType accountType, BigDecimal openingBalance) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Account name cannot be blank");
        }

        if (accountType == null) {
            throw new IllegalArgumentException("Account type cannot be null");
        }

        if (openingBalance == null) {
            throw new IllegalArgumentException("Opening balance cannot be null");
        }

        if (openingBalance.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Opening balance must be positive");
        }

        this.name = name;
        this.accountType = accountType;
        this.openingBalance = openingBalance;
    }

    // Method to assign an ID to the account with validation
    public void assignId(Long id) {
        if (this.id != null) {
            throw new IllegalStateException("Account already has an ID");
        }

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }

        this.id = id;
    }
}
