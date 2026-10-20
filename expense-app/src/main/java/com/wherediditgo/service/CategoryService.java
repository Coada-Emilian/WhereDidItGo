package com.wherediditgo.service;

import java.util.List;

import com.wherediditgo.domain.Category;
import com.wherediditgo.repository.CategoryRepository;

public class CategoryService {
  private final CategoryRepository repository;

  public CategoryService(CategoryRepository repository) {
    this.repository = repository;
  }

  public void createCategory(Category category) {
    repository.create(category);
  }

  public List<Category> getAllCategories() {
    return repository.findAll();
  }

  public Category getCategoryById(Long id) {
    return repository.findById(id);
  }

  public void updateCategory(Long id, Category category) {
    repository.update(id, category);
  }

  public void deleteCategory(Long id) {
    Category category = repository.findById(id);
    repository.delete(category);
  }
}