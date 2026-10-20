package com.wherediditgo.service;

import java.util.List;

import com.wherediditgo.domain.Category;
import com.wherediditgo.repository.CategoryRepository;

public class CategoryService {
  // Service class for managing categories
  private final CategoryRepository repository;

  // Constructor for initializing the service with a repository
  public CategoryService(CategoryRepository repository) {
    this.repository = repository;
  }

  // Method for creating a new category
  public void createCategory(Category category) {
    repository.create(category);
  }

  // Method for retrieving all categories
  public List<Category> getAllCategories() {
    return repository.findAll();
  }

  // Method for retrieving a category by its ID
  public Category getCategoryById(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("ID cannot be null");
    }

    Category category = repository.findById(id);

    if (category == null) {
      throw new IllegalArgumentException("Category with ID " + id + " does not exist");
    }

    return category;
  }

  // Method for updating an existing category
  public void updateCategory(Long id, Category category) {
    if (category == null) {
      throw new IllegalArgumentException("Category cannot be null");
    }

    getCategoryById(id);
    repository.update(id, category);
  }

  // Method for deleting a category by its ID
  public void deleteCategory(Long id) {
    Category category = getCategoryById(id);

    repository.delete(category);
  }
}