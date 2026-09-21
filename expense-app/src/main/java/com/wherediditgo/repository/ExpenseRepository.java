package com.wherediditgo.repository;

import java.util.ArrayList;
import java.util.List;

import com.wherediditgo.entity.Expense;

public class ExpenseRepository {

    private final List<Expense> expenses = new ArrayList<>();

    public void create(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> findAll() {
        return expenses;
    }

    public void delete(Expense expense) {
        expenses.remove(expense);
    }
}