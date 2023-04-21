package com.crs.kttr.product.ticket.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "train_ticket")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "id")
public class TrainTicket {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Integer maxQuantity;

  private Integer issueQuantity;

  public TrainTicket(String name, Integer maxQuantity) {
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
