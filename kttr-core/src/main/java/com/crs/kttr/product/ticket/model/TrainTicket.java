package com.crs.kttr.product.ticket.model;

import com.crs.kttr.global.ServerExceptionDefinedReason;
import com.crs.kttr.product.exception.TrainTicketOutOfStockException;
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

  public void issue() {
    if (isSoldOut(this.issueQuantity)) {
      throw new TrainTicketOutOfStockException(ServerExceptionDefinedReason.TRAIN_TICKET_OUT_OF_STOCK);
    }

    this.issueQuantity = this.issueQuantity + 1;
  }

  private Boolean isSoldOut(Integer issueQuantity) {
    return issueQuantity >= maxQuantity;
  }
}
