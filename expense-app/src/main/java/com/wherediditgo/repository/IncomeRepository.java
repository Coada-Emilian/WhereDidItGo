package com.wherediditgo.repository;

import java.util.ArrayList;
import java.util.List;

import com.wherediditgo.domain.Income;

public class IncomeRepository {

    private final List<Income> incomes = new ArrayList<>();

    private Long nextId = 1L;

    public void create(Income income) {
        if (income == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }

        income.assignId(nextId);
        nextId++;
        incomes.add(income);
    }

    public List<Income> findAll() {
        return new ArrayList<>(incomes);
    }

    public Income findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        return incomes.stream()
                .filter(income -> income.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void delete(Income income) {
        if (income == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }

        incomes.remove(income);
    }

}