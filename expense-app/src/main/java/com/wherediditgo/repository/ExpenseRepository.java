package com.wherediditgo.repository;

import java.util.ArrayList;
import java.util.List;

import com.wherediditgo.domain.Expense;

public class ExpenseRepository {

    private final List<Expense> expenses = new ArrayList<>();

    public void create(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Expense cannot be null");
        }

        expenses.add(expense);
    }

    public List<Expense> findAll() {
        return new ArrayList<>(expenses);
    }

    public Expense findById(Long id) {
        return expenses.stream()
                .filter(expense -> expense.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void delete(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Expense cannot be null");
        }

        expenses.remove(expense);
    }

}