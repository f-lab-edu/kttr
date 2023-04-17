package com.crs.kttr.reservation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainTicketReserveRequest {
  private Long memberId;

  private Long ticketId;
}
