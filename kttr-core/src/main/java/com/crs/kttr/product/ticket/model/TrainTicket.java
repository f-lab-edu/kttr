package com.crs.kttr.product.ticket.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "train_ticket")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "id")
@ToString
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

  public Integer issue() {
    if (issueQuantity < maxQuantity) {
      this.issueQuantity += 1;
      return this.issueQuantity;
    }

    return this.issueQuantity;
  }
}
