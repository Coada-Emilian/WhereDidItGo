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

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  public Category(String name) {
    this.name = name;
  }
}