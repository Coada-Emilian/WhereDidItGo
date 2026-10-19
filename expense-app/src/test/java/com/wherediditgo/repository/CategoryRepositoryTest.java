package com.wherediditgo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wherediditgo.domain.Category;

public class CategoryRepositoryTest {
  private CategoryRepository categoryRepository;

  @BeforeEach
  void setUp() {
    categoryRepository = new CategoryRepository();
  }

  @Test
  public void create_shouldSaveCategory() {
    Category category = new Category("Groceries");

    categoryRepository.create(category);

    assertEquals(1, categoryRepository.findAll().size());
    assertEquals(category, categoryRepository.findAll().get(0));
  }

  @Test
  public void create_shouldRejectNullCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> categoryRepository.create(null));
  }

  @Test
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

}
