package com.wherediditgo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.wherediditgo.domain.Account;
import com.wherediditgo.domain.AccountType;
import com.wherediditgo.domain.Income;
import com.wherediditgo.domain.IncomeSource;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class IncomeRepositoryTest {

    private IncomeRepository incomeRepository;

    // Set up the IncomeRepository before each test
    @BeforeEach
    void setUp() {
        incomeRepository = new IncomeRepository();
    }

    // CREATE

    // Test for creating a new income
    @Test
    public void create_shouldSaveIncome() {
        Account account = new Account(
                "Savings", AccountType.SAVINGS, new BigDecimal("1000.00"));

        Income income = new Income(
                new BigDecimal("4000.00"),
                LocalDate.now(),
                "Income from Twitch streaming",
                IncomeSource.OTHER,
                account);

        incomeRepository.create(income);

        assertEquals(1, incomeRepository.findAll().size());
        assertEquals(income, incomeRepository.findAll().get(0));
    }

    // Test for rejecting null income when creating a new income
    @Test
    public void create_shouldRejectNullIncome() {
        assertThrows(
                IllegalArgumentException.class,
                () -> incomeRepository.create(null));
    }

    // Test for assigning unique IDs to newly created incomes
    @Test
    public void create_shouldAssignUniqueIds() {
        Account account = new Account(
                "Savings", AccountType.SAVINGS, new BigDecimal("1000.00"));
        LocalDate currentDate = LocalDate.now();

        Income income1 = new Income(
                new BigDecimal("3000.00"),
                currentDate,
                "Income from side hustle",
                IncomeSource.OTHER,
                account);

        Income income2 = new Income(
                new BigDecimal("5000.00"),
                currentDate,
                "Income from second-hand market selling",
                IncomeSource.OTHER,
                account);

        incomeRepository.create(income1);
        incomeRepository.create(income2);

        assertNotNull(income1.getId());
        assertNotNull(income2.getId());
        assertEquals(1L, income1.getId());
        assertEquals(2L, income2.getId());
        assertNotEquals(income1.getId(), income2.getId());
    }

    // READ

    // Test for retrieving all incomes
    @Test
    public void findAll_shouldReturnAllIncomes() {
        Account account = new Account(
                "Savings", AccountType.SAVINGS, new BigDecimal("1000.00"));
        LocalDate currentDate = LocalDate.now();

        Income income1 = new Income(
                new BigDecimal("3000.00"),
                currentDate,
                "Income from side hustle",
                IncomeSource.OTHER,
                account);

        Income income2 = new Income(
                new BigDecimal("5000.00"),
                currentDate,
                "Income from second-hand market selling",
                IncomeSource.OTHER,
                account);

        incomeRepository.create(income1);
        incomeRepository.create(income2);

        var incomes = incomeRepository.findAll();

        assertEquals(2, incomes.size());
        assertEquals(income1, incomes.get(0));
        assertEquals(income2, incomes.get(1));
    }

    // Test for retrieving all incomes when none exist
    @Test
    public void findAll_shouldReturnEmptyListWhenNoIncomes() {
        assertEquals(0, incomeRepository.findAll().size());
    }

    // Test for retrieving an income by ID when it exists
    @Test
    public void findById_shouldReturnIncomeWhenExists() {
        Account account = new Account(
                "Savings", AccountType.SAVINGS, new BigDecimal("1000.00"));

        Income income = new Income(
                new BigDecimal("4000.00"),
                LocalDate.now(),
                "Income from Twitch streaming",
                IncomeSource.OTHER,
                account);

        incomeRepository.create(income);

        Income foundIncome = incomeRepository.findById(income.getId());

        assertEquals(income, foundIncome);
    }

    // Test for retrieving an income by ID when it does not exist
    @Test
    public void findById_shouldReturnNullWhenIncomeDoesNotExist() {
        assertNull(incomeRepository.findById(1L));
    }

    // Test for rejecting null ID when retrieving an income by ID
    @Test
    public void findById_shouldRejectNullId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> incomeRepository.findById(null));
    }

    // UPDATE

    // Test for updating an income
    @Test
    public void update_shouldUpdateIncomeAndPreserveId() {
        Account account = new Account(
                "Savings account", AccountType.SAVINGS, new BigDecimal("1000.00"));
        LocalDate currentDate = LocalDate.now();

        Income income = new Income(
                new BigDecimal("4000.00"),
                currentDate,
                "Income from Twitch streaming",
                IncomeSource.OTHER,
                account);

        incomeRepository.create(income);

        Long initialId = income.getId();

        Income updatedIncome = new Income(
                new BigDecimal("4500.00"),
                currentDate,
                "Updated Twitch streaming income",
                IncomeSource.OTHER,
                account);

        incomeRepository.update(initialId, updatedIncome);

        Income foundIncome = incomeRepository.findById(initialId);

        assertEquals(initialId, foundIncome.getId());
        assertEquals(new BigDecimal("4500.00"), foundIncome.getAmount());
        assertEquals("Updated Twitch streaming income", foundIncome.getDescription());
        assertEquals(1, incomeRepository.findAll().size());
        assertEquals(updatedIncome, foundIncome);
    }

    // Test for rejecting null ID when updating an income
    @Test
    public void update_shouldRejectNullId() {
        Account account = new Account(
                "Savings", AccountType.SAVINGS, new BigDecimal("1000.00"));

        Income updatedIncome = new Income(
                new BigDecimal("4500.00"),
                LocalDate.now(),
                "Updated income",
                IncomeSource.OTHER,
                account);

        assertThrows(
                IllegalArgumentException.class,
                () -> incomeRepository.update(null, updatedIncome));
    }

    // Test for rejecting null income when updating an income
    @Test
    public void update_shouldRejectNullIncome() {
        assertThrows(
                IllegalArgumentException.class,
                () -> incomeRepository.update(1L, null));
    }

    // Test for rejecting non-existent ID when updating an income
    @Test
    public void update_shouldRejectNonExistentId() {
        Account account = new Account(
                "Savings", AccountType.SAVINGS, new BigDecimal("1000.00"));

        Income updatedIncome = new Income(
                new BigDecimal("4500.00"),
                LocalDate.now(),
                "Updated income",
                IncomeSource.OTHER,
                account);

        assertThrows(
                IllegalArgumentException.class,
                () -> incomeRepository.update(99L, updatedIncome));
    }

    // DELETE

    // Test for deleting an income
    @Test
    public void delete_shouldRemoveIncome() {
        Account account = new Account(
                "Savings account", AccountType.SAVINGS, new BigDecimal("1000.00"));

        Income income = new Income(
                new BigDecimal("4000.00"),
                LocalDate.now(),
                "Income from Twitch streaming",
                IncomeSource.OTHER,
                account);

        incomeRepository.create(income);

        incomeRepository.delete(income);

        assertEquals(0, incomeRepository.findAll().size());
        assertNull(incomeRepository.findById(income.getId()));
    }

    // Test for rejecting null income when deleting an income
    @Test
    public void delete_shouldRejectNullIncome() {
        assertThrows(
                IllegalArgumentException.class,
                () -> incomeRepository.delete(null));
    }

    // Test for rejecting non-existent income when deleting an income
    @Test
    public void delete_shouldRejectNonExistentIncome() {
        Account account = new Account(
                "Savings account", AccountType.SAVINGS, new BigDecimal("1000.00"));

        Income income = new Income(
                new BigDecimal("4000.00"),
                LocalDate.now(),
                "Income from Twitch streaming",
                IncomeSource.OTHER,
                account);

        assertThrows(
                IllegalArgumentException.class,
                () -> incomeRepository.delete(income));
    }
}