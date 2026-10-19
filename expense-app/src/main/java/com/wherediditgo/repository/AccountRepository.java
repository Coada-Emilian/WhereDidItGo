package com.wherediditgo.repository;

import java.util.ArrayList;
import java.util.List;

import com.wherediditgo.domain.Account;

public class AccountRepository {

    private final List<Account> accounts = new ArrayList<>();

    private Long nextId = 1L;

    public void create(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        account.assignId(nextId);
        nextId++;
        accounts.add(account);
    }

    public List<Account> findAll() {
        return new ArrayList<>(accounts);
    }

    public Account findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        return accounts.stream()
                .filter(account -> account.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void delete(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        accounts.remove(account);
    }

    public void update(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        Account existingAccount = findById(account.getId());
        if (existingAccount == null) {
            throw new IllegalArgumentException("Account not found");
        }

        accounts.remove(existingAccount);
        accounts.add(account);
    }

}