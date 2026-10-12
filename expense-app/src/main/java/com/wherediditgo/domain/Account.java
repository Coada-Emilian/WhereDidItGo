package com.wherediditgo.domain;

public class Account {

    private Long id;
    private String name;
    private AccountType accountType;

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

    public Account(String name, AccountType accountType) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Account name cannot be blank");
        }

        if (accountType == null) {
            throw new IllegalArgumentException("Account type cannot be null");
        }

        this.name = name;
        this.accountType = accountType;
    }
}
