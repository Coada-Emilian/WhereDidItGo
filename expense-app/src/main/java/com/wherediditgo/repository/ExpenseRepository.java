package com.wherediditgo.repository;

import java.util.ArrayList;
import java.util.List;

import com.wherediditgo.domain.Expense;

public class ExpenseRepository {

    private final List<Expense> expenses = new ArrayList<>();

    private Long nextId = 1L;

    public void create(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Expense cannot be null");
        }

        expense.assignId(nextId);
        nextId++;
        expenses.add(expense);
    }

    public List<Expense> findAll() {
        return new ArrayList<>(expenses);
    }

    public Expense findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

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