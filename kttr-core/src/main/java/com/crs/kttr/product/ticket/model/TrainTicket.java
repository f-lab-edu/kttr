package com.crs.kttr.product.ticket.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Lock;

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

  @Version
  private Long version;

  public TrainTicket(String name, Integer maxQuantity) {
    this.name = name;
    this.maxQuantity = maxQuantity;
    this.issueQuantity = new AtomicInteger(0).intValue();
  }

  public void issue() {
    final AtomicInteger atomicIssueQuantity = new AtomicInteger(this.issueQuantity);
    if (isSoldOut(atomicIssueQuantity)) {
      return;
    }

    this.issueQuantity = atomicIssueQuantity.incrementAndGet();
  }

  private Boolean isSoldOut(AtomicInteger atomicIssueQuantity) {
    return atomicIssueQuantity.compareAndSet(maxQuantity, this.issueQuantity.intValue());
  }
}
