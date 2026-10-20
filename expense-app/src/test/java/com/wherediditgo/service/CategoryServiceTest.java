
package com.wherediditgo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.wherediditgo.domain.Category;
import com.wherediditgo.repository.CategoryRepository;

@TestMethodOrder(MethodOrderer.MethodName.class)
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
  public void getCategoryById_shouldThrowForNullId() {
    assertThrows(IllegalArgumentException.class,
        () -> service.getCategoryById(null));
  }

  @Test
  public void getCategoryById_shouldThrowForNonExistentCategory() {
    assertThrows(IllegalArgumentException.class,
        () -> service.getCategoryById(999L));
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
  public void updateCategory_shouldThrowForNullId() {
    Category entertainment = new Category("Entertainment");

    assertThrows(IllegalArgumentException.class,
        () -> service.updateCategory(null, entertainment));
  }

  @Test
  public void updateCategory_shouldThrowForNullCategory() {
    Category groceries = new Category("Groceries");
    service.createCategory(groceries);

    assertThrows(IllegalArgumentException.class,
        () -> service.updateCategory(groceries.getId(), null));
  }

  @Test
  public void updateCategory_shouldThrowForNonExistentCategory() {
    Category entertainment = new Category("Entertainment");

    assertThrows(IllegalArgumentException.class,
        () -> service.updateCategory(999L, entertainment));
  }

  @Test
  public void deleteCategory_shouldRemoveCategory() {
    Category groceries = new Category("Groceries");
    service.createCategory(groceries);

    Long id = groceries.getId();

    service.deleteCategory(id);

    assertEquals(0, service.getAllCategories().size());
  }

  @Test
  public void deleteCategory_shouldThrowForNullId() {
    assertThrows(IllegalArgumentException.class,
        () -> service.deleteCategory(null));
  }

  @Test
  public void deleteCategory_shouldThrowForNonExistentCategory() {
    assertThrows(IllegalArgumentException.class,
        () -> service.deleteCategory(999L));
  }
}
