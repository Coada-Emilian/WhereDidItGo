package com.wherediditgo.repository;

import java.util.ArrayList;
import java.util.List;

import com.wherediditgo.domain.Expense;

public class ExpenseRepository {

    // List to store all expenses
    private final List<Expense> expenses = new ArrayList<>();

    // Next ID to be assigned to a new expense
    private Long nextId = 1L;

    // Method to create a new expense with validation
    public void create(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Expense cannot be null");
        }

        expense.assignId(nextId);
        nextId++;
        expenses.add(expense);
    }

    // Method to retrieve all expenses
    public List<Expense> findAll() {
        return new ArrayList<>(expenses);
    }

    // Method to find an expense by its ID
    public Expense findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        return expenses.stream()
                .filter(expense -> expense.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Method to update an existing expense
    public void update(Long id, Expense updatedExpense) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        if (updatedExpense == null) {
            throw new IllegalArgumentException("Expense cannot be null");
        }

        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getId().equals(id)) {
                updatedExpense.assignId(id);
                expenses.set(i, updatedExpense);
                return;
            }
        }

        throw new IllegalArgumentException("Expense not found");
    }

    // Method to delete an expense
    public void delete(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Expense cannot be null");
        }

        if (!expenses.remove(expense)) {
            throw new IllegalArgumentException("Expense not found");
        }
    }
}