package com.crs.kttr.reservation.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainTicketReserveRequest {
  @NotNull
  private Long memberId;

  @NotNull
  private Long ticketId;
}
