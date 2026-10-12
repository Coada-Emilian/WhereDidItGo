package com.wherediditgo.repository;

import java.util.ArrayList;
import java.util.List;

import com.wherediditgo.domain.Expense;

public class ExpenseRepository {

    private final List<Expense> expenses = new ArrayList<>();

    public void create(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> findAll() {
        return expenses;
    }

    // Find an expense by its ID (commented out)
    // public Expense findById(int id) {
    // return expenses.stream()
    // .filter(expense -> expense.getId().equals(id))
    // .findFirst()
    // .orElse(null);
    // }

    public void delete(Expense expense) {
        expenses.remove(expense);
    }
}