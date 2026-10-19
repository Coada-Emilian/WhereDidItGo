
package com.wherediditgo.repository;

import java.util.ArrayList;
import java.util.List;

import com.wherediditgo.domain.Category;

public class CategoryRepository {

  private final List<Category> categories = new ArrayList<>();

  private Long nextId = 1L;

  public void create(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("Category cannot be null");
    }

    category.assignId(nextId);
    nextId++;
    categories.add(category);
  }

  public List<Category> findAll() {
    return new ArrayList<>(categories);
  }

  public Category findById(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("ID cannot be null");
    }

    return categories.stream()
        .filter(category -> category.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  public void delete(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("Category cannot be null");
    }

    categories.remove(category);
  }

  public void update(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("Category cannot be null");
    }

    Category existingCategory = findById(category.getId());
    if (existingCategory == null) {
      throw new IllegalArgumentException("Category not found");
    }

    categories.remove(existingCategory);
    categories.add(category);
  }
}