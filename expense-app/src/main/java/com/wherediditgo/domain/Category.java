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
}