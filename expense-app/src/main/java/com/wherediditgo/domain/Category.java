package com.wherediditgo.domain;

public class Category {

  private Long id;
  private String name;

  /**
   * @return Long return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @return String return the name
   */
  public String getName() {
    return name;
  }

  public Category(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Category name cannot be blank");
    }

    this.name = name;
  }

  public void assignId(Long id) {
    if (this.id != null) {
      throw new IllegalStateException("Category already has an ID");
    }

    if (id == null || id <= 0) {
      throw new IllegalArgumentException("ID must be positive");
    }

    this.id = id;
  }
}