package com.wherediditgo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wherediditgo.domain.Category;

public class CategoryRepositoryTest {

  private CategoryRepository categoryRepository;

  // Initialize the CategoryRepository before each test
  @BeforeEach
  void setUp() {
    categoryRepository = new CategoryRepository();
  }

  // CREATE

  // Test for creating categories
  @Test
  public void create_shouldSaveCategory() {
    Category category = new Category("Groceries");

    categoryRepository.create(category);

    assertEquals(1, categoryRepository.findAll().size());
    assertEquals(category, categoryRepository.findAll().get(0));
  }

  // Test for rejecting null categories
  @Test
  public void create_shouldRejectNullCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> categoryRepository.create(null));
  }

  // Test for assigning unique IDs to categories
  @Test
  public void create_shouldAssignUniqueIds() {
    Category category1 = new Category("Groceries");
    Category category2 = new Category("Utilities");

    categoryRepository.create(category1);
    categoryRepository.create(category2);

    assertNotNull(category1.getId());
    assertNotNull(category2.getId());
    assertEquals(1L, category1.getId());
    assertEquals(2L, category2.getId());
    assertNotEquals(category1.getId(), category2.getId());
  }

  // READ

  // Test for retrieving all categories
  @Test
  public void findAll_shouldReturnAllCategories() {
    Category category1 = new Category("Groceries");
    Category category2 = new Category("Utilities");

    categoryRepository.create(category1);
    categoryRepository.create(category2);

    assertEquals(2, categoryRepository.findAll().size());
    assertEquals(category1, categoryRepository.findAll().get(0));
    assertEquals(category2, categoryRepository.findAll().get(1));
  }

  // Test for retrieving all categories when none exist
  @Test
  public void findAll_shouldReturnEmptyListWhenNoCategories() {
    assertEquals(0, categoryRepository.findAll().size());
  }

  // Test for retrieving a category by ID when it exists
  @Test
  public void findById_shouldReturnCategoryWhenExists() {
    Category category = new Category("Groceries");
    categoryRepository.create(category);

    Category foundCategory = categoryRepository.findById(category.getId());

    assertEquals(category, foundCategory);
  }

  // Test for retrieving a category by ID when it does not exist
  @Test
  public void findById_shouldReturnNullWhenCategoryDoesNotExist() {
    assertNull(categoryRepository.findById(1L));
  }

  // Test for rejecting null ID when retrieving a category by ID
  @Test
  public void findById_shouldRejectNullId() {
    assertThrows(
        IllegalArgumentException.class,
        () -> categoryRepository.findById(null));
  }

  // UPDATE

  // Test for updating categories
  @Test
  public void update_shouldUpdateCategoryAndPreserveId() {
    Category category = new Category("Groceries");
    categoryRepository.create(category);

    Long initialId = category.getId();
    Category updatedCategory = new Category("Games");

    categoryRepository.update(initialId, updatedCategory);

    Category foundCategory = categoryRepository.findById(initialId);

    assertEquals(initialId, foundCategory.getId());
    assertEquals("Games", foundCategory.getName());
    assertEquals(1, categoryRepository.findAll().size());
    assertEquals(updatedCategory, foundCategory);
  }

  // Test for rejecting null ID when updating a category
  @Test
  public void update_shouldRejectNullId() {
    Category updatedCategory = new Category("Consoles");

    assertThrows(
        IllegalArgumentException.class,
        () -> categoryRepository.update(null, updatedCategory));
  }

  // Test for rejecting null category when updating a category
  @Test
  public void update_shouldRejectNullCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> categoryRepository.update(1L, null));
  }

  // Test for rejecting non-existent ID when updating a category
  @Test
  public void update_shouldRejectNonExistentId() {
    Category updatedCategory = new Category("Consoles");

    assertThrows(
        IllegalArgumentException.class,
        () -> categoryRepository.update(99L, updatedCategory));
  }

  // DELETE

  // Test for deleting categories
  @Test
  public void delete_shouldRemoveCategory() {
    Category category = new Category("Groceries");
    categoryRepository.create(category);

    categoryRepository.delete(category);

    assertEquals(0, categoryRepository.findAll().size());
    assertNull(categoryRepository.findById(category.getId()));
  }

  // Test for rejecting null category when deleting a category
  @Test
  public void delete_shouldRejectNullCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> categoryRepository.delete(null));
  }

  // Test for rejecting non-existent category when deleting a category
  @Test
  public void delete_shouldRejectNonExistentCategory() {
    Category category = new Category("Games");

    assertThrows(
        IllegalArgumentException.class,
        () -> categoryRepository.delete(category));
  }
}