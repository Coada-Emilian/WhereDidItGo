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
import com.wherediditgo.domain.Category;
import com.wherediditgo.domain.Expense;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class ExpenseRepositoryTest {

    private ExpenseRepository repository;

    // Setup method to initialize the repository before each test
    @BeforeEach
    void setUp() {
        repository = new ExpenseRepository();
    }

    // CREATE

    // Test for creating expenses
    @Test
    public void create_shouldStoreExpense() {
        Account account = new Account(
                "Main account", AccountType.CHECKING, new BigDecimal("100.00"));

        Category category = new Category("Groceries");

        Expense expense = new Expense(
                new BigDecimal("25.50"),
                LocalDate.of(2026, 10, 12),
                "Weekly shopping",
                category,
                account);

        repository.create(expense);

        assertEquals(1, repository.findAll().size());
        assertEquals(expense, repository.findAll().get(0));
    }

    // Test for rejecting null expenses when creating an expense
    @Test
    public void create_shouldRejectNullExpense() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.create(null));
    }

    // Test for assigning unique IDs to created expenses
    @Test
    public void create_shouldAssignUniqueIds() {
        Account account = new Account(
                "Main account", AccountType.CHECKING, new BigDecimal("100.00"));
        Category category = new Category("Groceries");

        Expense expense1 = new Expense(
                new BigDecimal("25.50"),
                LocalDate.of(2026, 10, 12),
                "Weekly shopping",
                category,
                account);

        Expense expense2 = new Expense(
                new BigDecimal("30.00"),
                LocalDate.of(2026, 10, 13),
                "Extra shopping",
                category,
                account);

        repository.create(expense1);
        repository.create(expense2);

        assertNotNull(expense1.getId());
        assertNotNull(expense2.getId());
        assertEquals(1L, expense1.getId());
        assertEquals(2L, expense2.getId());
        assertNotEquals(expense1.getId(), expense2.getId());
    }

    // READ

    // Test for retrieving all expenses
    @Test
    public void findAll_shouldReturnAllExpenses() {
        Account account1 = new Account(
                "Main account", AccountType.CHECKING, new BigDecimal("100.00"));

        Category category1 = new Category("Groceries");

        Expense expense1 = new Expense(
                new BigDecimal("25.50"),
                LocalDate.of(2026, 10, 12),
                "Weekly shopping",
                category1,
                account1);

        Account account2 = new Account(
                "Savings account", AccountType.SAVINGS, new BigDecimal("500.00"));

        Category category2 = new Category("Entertainment");

        Expense expense2 = new Expense(
                new BigDecimal("15.00"),
                LocalDate.of(2026, 10, 13),
                "Movie ticket",
                category2,
                account2);

        repository.create(expense1);
        repository.create(expense2);

        var expenses = repository.findAll();

        assertEquals(2, expenses.size());
        assertEquals(expense1, expenses.get(0));
        assertEquals(expense2, expenses.get(1));
    }

    // Test for retrieving all expenses when none exist
    @Test
    public void findAll_shouldReturnEmptyListWhenNoExpenses() {
        assertEquals(0, repository.findAll().size());
    }

    // Test for retrieving an expense by ID when it exists
    @Test
    public void findById_shouldReturnExpenseWhenExists() {
        Account account = new Account(
                "Savings account", AccountType.SAVINGS, new BigDecimal("500.00"));
        Category category = new Category("Entertainment");

        Expense expense = new Expense(
                new BigDecimal("100.00"),
                LocalDate.of(2026, 10, 14),
                "Weekend fun",
                category,
                account);

        repository.create(expense);

        Expense foundExpense = repository.findById(expense.getId());

        assertEquals(expense, foundExpense);
    }

    // Test for retrieving an expense by ID when it does not exist
    @Test
    public void findById_shouldReturnNullWhenExpenseDoesNotExist() {
        assertNull(repository.findById(1L));
    }

    // Test for rejecting null ID when retrieving an expense by ID
    @Test
    public void findById_shouldRejectNullId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.findById(null));
    }

    // UPDATE

    // Test for updating expenses
    @Test
    public void update_shouldUpdateExpenseAndPreserveId() {
        Account account = new Account(
                "Savings account", AccountType.SAVINGS, new BigDecimal("500.00"));
        Category category = new Category("Entertainment");

        Expense expense = new Expense(
                new BigDecimal("100.00"),
                LocalDate.of(2026, 10, 14),
                "Weekend fun",
                category,
                account);

        repository.create(expense);

        Long initialId = expense.getId();

        Expense updatedExpense = new Expense(
                new BigDecimal("150.00"),
                LocalDate.of(2026, 10, 15),
                "Weekend fun updated",
                category,
                account);

        repository.update(initialId, updatedExpense);

        Expense foundExpense = repository.findById(initialId);

        assertEquals(initialId, foundExpense.getId());
        assertEquals(new BigDecimal("150.00"), foundExpense.getAmount());
        assertEquals(LocalDate.of(2026, 10, 15), foundExpense.getDate());
        assertEquals("Weekend fun updated", foundExpense.getDescription());
        assertEquals(1, repository.findAll().size());
        assertEquals(updatedExpense, foundExpense);
    }

    // Test for rejecting null ID when updating an expense
    @Test
    public void update_shouldRejectNullId() {
        Account account = new Account(
                "Main account", AccountType.CHECKING, new BigDecimal("100.00"));
        Category category = new Category("Groceries");

        Expense updatedExpense = new Expense(
                new BigDecimal("25.50"),
                LocalDate.of(2026, 10, 12),
                "Shopping",
                category,
                account);

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.update(null, updatedExpense));
    }

    // Test for rejecting null expense when updating an expense
    @Test
    public void update_shouldRejectNullExpense() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.update(1L, null));
    }

    // Test for rejecting non-existent ID when updating an expense
    @Test
    public void update_shouldRejectNonExistentId() {
        Account account = new Account(
                "Main account", AccountType.CHECKING, new BigDecimal("100.00"));
        Category category = new Category("Groceries");

        Expense updatedExpense = new Expense(
                new BigDecimal("25.50"),
                LocalDate.of(2026, 10, 12),
                "Shopping",
                category,
                account);

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.update(99L, updatedExpense));
    }

    // DELETE

    // Test for deleting expenses
    @Test
    public void delete_shouldRemoveExpense() {
        Account account = new Account(
                "Savings account", AccountType.SAVINGS, new BigDecimal("500.00"));
        Category category = new Category("Entertainment");

        Expense expense = new Expense(
                new BigDecimal("100.00"),
                LocalDate.of(2026, 10, 14),
                "Weekend fun",
                category,
                account);

        repository.create(expense);

        repository.delete(expense);

        assertEquals(0, repository.findAll().size());
        assertNull(repository.findById(expense.getId()));
    }

    // Test for rejecting null expense when deleting an expense
    @Test
    public void delete_shouldRejectNullExpense() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.delete(null));
    }

    // Test for rejecting non-existent expense when deleting an expense
    @Test
    public void delete_shouldRejectNonExistentExpense() {
        Account account = new Account(
                "Savings account", AccountType.SAVINGS, new BigDecimal("500.00"));
        Category category = new Category("Entertainment");

        Expense expense = new Expense(
                new BigDecimal("100.00"),
                LocalDate.of(2026, 10, 14),
                "Weekend fun",
                category,
                account);

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.delete(expense));
    }
}