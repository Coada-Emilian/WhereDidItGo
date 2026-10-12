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
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return AccountType return the type
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the type to set
     */
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Account(String name, AccountType accountType) {
        this.name = name;
        this.accountType = accountType;
    }

}
