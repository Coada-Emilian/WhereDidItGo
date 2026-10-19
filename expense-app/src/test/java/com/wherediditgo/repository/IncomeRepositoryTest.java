package com.wherediditgo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wherediditgo.domain.Account;
import com.wherediditgo.domain.AccountType;
import com.wherediditgo.domain.Income;
import com.wherediditgo.domain.IncomeSource;

public class IncomeRepositoryTest {
  // Test class for IncomeRepository
  private IncomeRepository incomeRepository;

  // Initialize the IncomeRepository before each test
  @BeforeEach
  public void setUp() {
    incomeRepository = new IncomeRepository();
  }

  // Test the creation of an income
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

  // Test that creating an income assigns a unique ID
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

  // Test that findAll returns all created incomes
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

  // Test that findAll returns an empty list when no incomes exist
  @Test
  public void findAll_shouldReturnEmptyListWhenNoIncomes() {
    assertEquals(0, incomeRepository.findAll().size());
  }

  // Test that findById returns the correct income when it exists
  @Test
  public void findById_shouldReturnIncomeWhenExists() {
    Account account = new Account("Savings", AccountType.SAVINGS);

    LocalDate currentDate = LocalDate.now();

    Income income = new Income((BigDecimal.valueOf(4000.0)), currentDate, "Income from Twitch streaming",
        IncomeSource.OTHER,
        account);
    incomeRepository.create(income);

    Income foundIncome = incomeRepository.findById(income.getId());

    assertEquals(income, foundIncome);
  }

  // Test that findById returns null when the income does not exist
  @Test
  public void findById_shouldNotReturnIncomeIfNoneExists() {
    assertEquals(null, incomeRepository.findById(1L));
  }

  // Test that findById throws an exception when given a null ID
  @Test
  public void findById_shouldRejectNullId() {
    assertThrows(
        IllegalArgumentException.class,
        () -> incomeRepository.findById(null));
  }

  // Test that delete removes the specified income
  @Test
  public void delete_shouldDeleteIncome() {
    Account account = new Account("Savings account", AccountType.SAVINGS);
    LocalDate currentDate = LocalDate.now();

    Income income = new Income((BigDecimal.valueOf(4000.0)), currentDate, "Income from Twitch streaming",
        IncomeSource.OTHER,
        account);
    incomeRepository.create(income);

    assertEquals(1, incomeRepository.findAll().size());

    Income foundIncome = incomeRepository.findAll().get(0);

    incomeRepository.delete(foundIncome);

    assertEquals(0, incomeRepository.findAll().size());
  }

  // Test for rejecting a null income when deleting
  @Test
  public void delete_shouldRejectNullIncome() {
    assertThrows(
        IllegalArgumentException.class,
        () -> incomeRepository.delete(null));
  }

  // Test that update correctly modifies an existing income
  @Test
  public void update_shouldUpdateIncome() {
    Account account = new Account("Savings account", AccountType.SAVINGS);
    LocalDate currentDate = LocalDate.now();

    Income income = new Income((BigDecimal.valueOf(4000.0)), currentDate, "Income from Twitch streaming",
        IncomeSource.OTHER,
        account);
    incomeRepository.create(income);

    Income updatedIncome = new Income((BigDecimal.valueOf(4500.0)), currentDate,
        "Second Income from Twitch streaming",
        IncomeSource.OTHER,
        account);

    updatedIncome.assignId(income.getId());

    incomeRepository.update(updatedIncome);

    var foundIncome = incomeRepository.findById(income.getId());
    assertEquals(updatedIncome, foundIncome);
  }

  // Test that update throws an exception when given a null income
  @Test
  public void update_shouldRejectNullIncome() {
    assertThrows(
        IllegalArgumentException.class,
        () -> incomeRepository.update(null));
  }
}