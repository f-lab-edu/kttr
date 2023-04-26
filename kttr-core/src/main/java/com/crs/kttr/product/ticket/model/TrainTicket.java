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

//  @Version
//  private Long version;

  public TrainTicket(String name, Integer maxQuantity) {
    this.name = name;
    this.maxQuantity = maxQuantity;
    this.issueQuantity = 0;
  }

  public void issue() {
    if (isSoldOut(this.issueQuantity)) {
      return;
    }

    this.issueQuantity = this.issueQuantity + 1;
  }

  private Boolean isSoldOut(Integer issueQuantity) {
    return issueQuantity >= maxQuantity;
  }
}
