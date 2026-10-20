package com.wherediditgo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wherediditgo.domain.Category;
import com.wherediditgo.repository.CategoryRepository;

public class CategoryServiceTest {
  private CategoryService service;
  private CategoryRepository repository;

  @BeforeEach
  public void setUp() {
    repository = new CategoryRepository();
    service = new CategoryService(repository);
  }

  @Test
  public void createCategory_shouldSaveCategory() {
    Category category = new Category("Food");

    service.createCategory(category);

    List<Category> categories = service.getAllCategories();

    assertEquals(1, categories.size());
    assertEquals("Food", categories.get(0).getName());
  }

  @Test
  public void getAllCategories_shouldReturnAllCategories() {
    Category food = new Category("Food");
    Category transport = new Category("Transport");
    Category entertainment = new Category("Entertainment");

    service.createCategory(food);
    service.createCategory(transport);
    service.createCategory(entertainment);

    List<Category> categories = service.getAllCategories();

    assertEquals(3, categories.size());
    assertEquals("Food", categories.get(0).getName());
    assertEquals("Transport", categories.get(1).getName());
    assertEquals("Entertainment", categories.get(2).getName());
  }

  @Test
  public void getCategoryById_shouldReturnCategory() {
    Category groceries = new Category("Groceries");
    service.createCategory(groceries);

    Category retrieved = service.getCategoryById(groceries.getId());

    assertEquals("Groceries", retrieved.getName());
  }

  @Test
  public void updateCategory_shouldModifyCategory() {
    Category groceries = new Category("Groceries");
    service.createCategory(groceries);

    Long id = groceries.getId();

    Category entertainment = new Category("Entertainment");

    service.updateCategory(id, entertainment);

    Category updated = service.getCategoryById(id);
    assertEquals(id, updated.getId());
    assertEquals("Entertainment", updated.getName());
  }

  @Test
  public void deleteCategory_shouldRemoveCategory() {
    Category groceries = new Category("Groceries");
    service.createCategory(groceries);

    Long id = groceries.getId();

    service.deleteCategory(id);

    List<Category> categories = service.getAllCategories();
    assertEquals(0, categories.size());
  }
}