package com.crs.kttr.product.ticket.model;

import lombok.ToString;

@ToString
public class Ticket {
  private Integer maxQuantity;

  private Integer issueQuantity;

  public Ticket() {
    this.maxQuantity = 100;
    this.issueQuantity = 0;
  }

  public void issue() {
    if (issueQuantity < maxQuantity) {
      issueQuantity++;
    }
  }
}
