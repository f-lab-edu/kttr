package com.crs.kttr.product.ticket.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.concurrent.atomic.AtomicBoolean;
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

  private Integer issueQuantity;

  private Boolean updating;

  public TrainTicket(String name, Integer maxQuantity) {
    this.name = name;
    this.maxQuantity = maxQuantity;
    this.issueQuantity = new AtomicInteger(0).intValue();
    this.updating = Boolean.FALSE;
  }

  public void issue() {
    final AtomicBoolean atomicUpdating = new AtomicBoolean(this.updating);
    if (atomicUpdating.get()) {
      return;
    }

    this.updating = !this.updating;

    final AtomicInteger atomicIssueQuantity = new AtomicInteger(this.issueQuantity);

    if (!atomicIssueQuantity.compareAndSet(maxQuantity, this.issueQuantity.intValue())) {
      this.issueQuantity = atomicIssueQuantity.incrementAndGet();
    }

    this.updating = !this.updating;
  }
}
