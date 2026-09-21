package com.wherediditgo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Income {
  private Long id;
  private BigDecimal amount;
  private LocalDate date;
  private String description;
  private String source;
  private Account account;
}