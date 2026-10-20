
package com.wherediditgo.repository;

import java.util.ArrayList;
import java.util.List;

import com.wherediditgo.domain.Category;

public class CategoryRepository {

  // List to store all categories
  private final List<Category> categories = new ArrayList<>();

  // Next ID to be assigned to a new category
  private Long nextId = 1L;

  // Method to create a new category with validation
  public void create(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("Category cannot be null");
    }

    category.assignId(nextId);
    nextId++;
    categories.add(category);
  }

  // Method to retrieve all categories
  public List<Category> findAll() {
    return new ArrayList<>(categories);
  }

  // Method to find a category by its ID
  public Category findById(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("ID cannot be null");
    }

    return categories.stream()
        .filter(category -> category.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  // Method to update an existing category
  public void update(Long id, Category updatedCategory) {
    if (id == null) {
      throw new IllegalArgumentException("ID cannot be null");
    }

    if (updatedCategory == null) {
      throw new IllegalArgumentException("Category cannot be null");
    }

    for (int i = 0; i < categories.size(); i++) {
      if (categories.get(i).getId().equals(id)) {
        updatedCategory.assignId(id);
        categories.set(i, updatedCategory);
        return;
      }
    }

    throw new IllegalArgumentException("Category not found");
  }

  // Method to delete a category
  public void delete(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("Category cannot be null");
    }

    if (!categories.remove(category)) {
      throw new IllegalArgumentException("Category not found");
    }
  }
}