package com.crs.kttr.product.ticket.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

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

  private AtomicInteger issueQuantity;

  public TrainTicket(String name, Integer maxQuantity) {
    this.name = name;
    this.maxQuantity = maxQuantity;
    this.issueQuantity = new AtomicInteger(0);
  }

  public Integer issue() {
    if (!issueQuantity.compareAndSet(maxQuantity, this.issueQuantity.intValue())) {
      // incrementAndGet
      this.issueQuantity.getAndIncrement();
      return this.issueQuantity.intValue();
    }

    return this.issueQuantity.intValue();
  }
}
