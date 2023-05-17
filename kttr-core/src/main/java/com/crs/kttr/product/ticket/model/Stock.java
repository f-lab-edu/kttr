package com.crs.kttr.product.ticket.model;

import lombok.Getter;

@Getter
public class Stock {

  private String name;

  private String ticketId;

  private int amount;


  public Stock(String name, Long ticketId, int amount) {
    this.name = name;
    this.amount = amount;
    this.ticketId = String.valueOf(ticketId);
  }

  public String getKeyId() {
    return this.ticketId;
  }
}
