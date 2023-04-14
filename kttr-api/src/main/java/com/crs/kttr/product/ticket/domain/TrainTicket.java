package com.crs.kttr.product.ticket.domain;

public class TrainTicket {
  private Long id;

  private String name;

  private Integer maxQuantity;

  private Integer issueQuantity;

  public TrainTicket(Long id, String name, Integer maxQuantity) {
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

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getMaxQuantity() {
    return maxQuantity;
  }

  public Integer getIssueQuantity() {
    return issueQuantity;
  }
}
