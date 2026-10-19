package com.wherediditgo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wherediditgo.domain.Category;

public class CategoryRepositoryTest {
  // Test class for CategoryRepository
  private CategoryRepository categoryRepository;

  // Test setup method to initialize the repository before each test
  @BeforeEach
  void setUp() {
    categoryRepository = new CategoryRepository();
  }

  // Test method for creating a category
  @Test
  public void create_shouldSaveCategory() {
    Category category = new Category("Groceries");

    categoryRepository.create(category);

    assertEquals(1, categoryRepository.findAll().size());
    assertEquals(category, categoryRepository.findAll().get(0));
  }

  // Test method for rejecting null category
  @Test
  public void create_shouldRejectNullCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> categoryRepository.create(null));
  }

  @Test
  // Test method for assigning unique IDs to categories
  public void create_shouldAssignUniqueIds() {
    Category category1 = new Category("Groceries");
    Category category2 = new Category("Utilities");

    categoryRepository.create(category1);
    categoryRepository.create(category2);

    assertEquals(true, category1.getId() != null);
    assertEquals(true, category2.getId() != null);

    assertEquals(1L, category1.getId());
    assertEquals(2L, category2.getId());

    assertEquals(true, !category1.getId().equals(category2.getId()));
  }

  @Test
  // Test method for finding all categories
  public void findAll_shouldReturnAllCategories() {
    Category category1 = new Category("Groceries");
    Category category2 = new Category("Utilities");

    categoryRepository.create(category1);
    categoryRepository.create(category2);

    assertEquals(2, categoryRepository.findAll().size());
    assertEquals(category1, categoryRepository.findAll().get(0));
    assertEquals(category2, categoryRepository.findAll().get(1));
  }

  @Test
  // Test method for finding all categories when none exist
  public void findAll_shouldReturnEmptyListWhenNoCategories() {
    assertEquals(0, categoryRepository.findAll().size());
  }

  // Test method for finding a category by ID when it exists
  @Test
  public void findById_shouldReturnCategoryWhenExists() {
    Category category = new Category("Groceries");
    categoryRepository.create(category);

    var foundCategory = categoryRepository.findById(category.getId());

    assertEquals(category, foundCategory);
  }

  // Test method for finding a category by ID when it does not exist
  @Test
  public void findById_shouldReturnNullWhenCategoryDoesNotExist() {
    var foundCategory = categoryRepository.findById(1L);

    assertEquals(null, foundCategory);
  }

  @Test
  // Test method for rejecting null ID when finding a category by ID
  public void findById_shouldRejectNullId() {
    assertThrows(
        IllegalArgumentException.class,
        () -> categoryRepository.findById(null));
  }

  // Test method for removing a category
  @Test
  public void delete_shouldRemoveCategory() {
    Category category = new Category("Groceries");
    categoryRepository.create(category);

    assertEquals(1, categoryRepository.findAll().size());

    Category foundCategory = categoryRepository.findById(category.getId());

    categoryRepository.delete(foundCategory);

    assertEquals(0, categoryRepository.findAll().size());
  }

  // Test method for rejecting null category when deleting
  @Test
  public void delete_shouldRejectNullCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> categoryRepository.delete(null));
  }

  // Test method for rejecting non-existent category when deleting
  @Test
  public void delete_shouldRejectNonExistentCategory() {
    Category category = new Category("Non-existent category");
    assertThrows(
        IllegalArgumentException.class,
        () -> categoryRepository.delete(category));
  }

  // Test method for updating a category
  @Test
  public void update_shouldUpdateCategory() {
    Category category = new Category("Groceries");
    categoryRepository.create(category);

    Category newCategory = new Category("Games");

    newCategory.assignId(category.getId());

    categoryRepository.update(newCategory);

    Category foundCategory = categoryRepository.findById(category.getId());
    assertEquals(newCategory, foundCategory);

  }

  // Test method for rejecting null category when updating
  @Test
  public void update_shouldRejectNullCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> categoryRepository.update(null));
  }
}
