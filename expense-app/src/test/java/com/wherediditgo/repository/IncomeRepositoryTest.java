package com.wherediditgo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wherediditgo.domain.Account;
import com.wherediditgo.domain.AccountType;
import com.wherediditgo.domain.Income;
import com.wherediditgo.domain.IncomeSource;

public class IncomeRepositoryTest {
  private IncomeRepository incomeRepository;

  @BeforeEach
  public void setUp() {
    incomeRepository = new IncomeRepository();
  }

  @Test
  public void create_shouldCreateIncome() {
    Account account = new Account("Savings", AccountType.SAVINGS);

    LocalDate currentDate = LocalDate.now();

    Income income = new Income((BigDecimal.valueOf(4000.0)), currentDate, "Income from Twitch streaming",
        IncomeSource.OTHER,
        account);
    incomeRepository.create(income);

    assertEquals(1, incomeRepository.findAll().size());

  }

  @Test
  public void create_shouldAssignUniqueIdToIncome() {
    Account account = new Account("Savings", AccountType.SAVINGS);

    LocalDate currentDate = LocalDate.now();

    Income income1 = new Income((BigDecimal.valueOf(3000.0)), currentDate, "Income from side hustle.",
        IncomeSource.OTHER,
        account);

    incomeRepository.create(income1);

    Income income2 = new Income((BigDecimal.valueOf(5000.0)), currentDate,
        "Income from second hand market selling.",
        IncomeSource.OTHER,
        account);

    incomeRepository.create(income2);

    assertEquals(true, income1.getId() != null);
    assertEquals(true, income2.getId() != null);

    assertEquals(1L, income1.getId());
    assertEquals(2L, income2.getId());

    assertEquals(true, !income1.getId().equals(income2.getId()));
  }

  @Test
  public void findAll_shouldReturnAllIncomes() {
    Account account = new Account("Savings", AccountType.SAVINGS);

    LocalDate currentDate = LocalDate.now();

    Income income1 = new Income((BigDecimal.valueOf(3000.0)), currentDate, "Income from side hustle.",
        IncomeSource.OTHER,
        account);

    incomeRepository.create(income1);

    Income income2 = new Income((BigDecimal.valueOf(5000.0)), currentDate,
        "Income from second hand market selling.",
        IncomeSource.OTHER,
        account);

    incomeRepository.create(income2);

    assertEquals(2, incomeRepository.findAll().size());
  }

}