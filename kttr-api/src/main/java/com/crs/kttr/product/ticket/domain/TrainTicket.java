package com.crs.kttr.product.ticket.domain;

import lombok.Getter;

@Getter
public class TrainTicket {
  private Long id;

  private String name;

  private Integer maxQuantity;

  private Integer issueQuantity;

  public TrainTicket(Long id, String name, Integer maxQuantity) {
    if (id == null || id < 1L) {
      throw new IllegalArgumentException();
    }

    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException();
    }

    if (maxQuantity == null || maxQuantity < 1) {
      throw new IllegalArgumentException();
    }

    this.id = id;
    this.name = name;
    this.maxQuantity = maxQuantity;
    this.issueQuantity = 0;
  }

  public void issue() {
    if (issueQuantity < maxQuantity) {
      this.issueQuantity++;

      return;
    }
    throw new RuntimeException();
  }
}
