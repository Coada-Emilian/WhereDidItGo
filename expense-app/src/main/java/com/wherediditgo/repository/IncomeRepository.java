package com.wherediditgo.repository;

import java.util.ArrayList;
import java.util.List;

import com.wherediditgo.domain.Income;

public class IncomeRepository {

    // List to store all incomes
    private final List<Income> incomes = new ArrayList<>();

    // Next ID to be assigned to a new income
    private Long nextId = 1L;

    // Method to create a new income with validation
    public void create(Income income) {
        if (income == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }

        income.assignId(nextId);
        nextId++;
        incomes.add(income);
    }

    // Method to retrieve all incomes
    public List<Income> findAll() {
        return new ArrayList<>(incomes);
    }

    // Method to find an income by its ID
    public Income findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        return incomes.stream()
                .filter(income -> income.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Method to update an existing income by its ID and instance
    public void update(Long id, Income updatedIncome) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        if (updatedIncome == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }

        for (int i = 0; i < incomes.size(); i++) {
            if (incomes.get(i).getId().equals(id)) {
                updatedIncome.assignId(id);
                incomes.set(i, updatedIncome);
                return;
            }
        }

        throw new IllegalArgumentException("Income not found");
    }

    // Method to delete an income by its instance
    public void delete(Income income) {
        if (income == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }

        if (!incomes.remove(income)) {
            throw new IllegalArgumentException("Income not found");
        }
    }
}