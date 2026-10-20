package com.wherediditgo.repository;

import java.util.ArrayList;
import java.util.List;

import com.wherediditgo.domain.Account;

public class AccountRepository {

    // List to store all accounts
    private final List<Account> accounts = new ArrayList<>();

    // Next ID to be assigned to a new account
    private Long nextId = 1L;

    // Method to create a new account with validation
    public void create(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        account.assignId(nextId);
        nextId++;
        accounts.add(account);
    }

    // Method to retrieve all accounts
    public List<Account> findAll() {
        return new ArrayList<>(accounts);
    }

    // Method to find an account by its ID
    public Account findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        return accounts.stream()
                .filter(account -> account.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Method to update an existing account
    public void update(Long id, Account updatedAccount) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        if (updatedAccount == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId().equals(id)) {
                updatedAccount.assignId(id);
                accounts.set(i, updatedAccount);
                return;
            }
        }

        throw new IllegalArgumentException("Account not found");
    }

    // Method to delete an account
    public void delete(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        if (!accounts.remove(account)) {
            throw new IllegalArgumentException("Account does not exist");
        }
    }
}