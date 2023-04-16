package com.crs.kttr.reservation.domain;

import java.util.UUID;

public class ReservationDetails {
  private String reservationCode;

  private Long memberId;

  private Long ticketId;

  public ReservationDetails(Long memberId, Long ticketId) {
    this.reservationCode = UUID.randomUUID().toString();
    this.memberId = memberId;
    this.ticketId = ticketId;
  }

  public String getReservationCode() {
    return reservationCode;
  }

  public Long getMemberId() {
    return memberId;
  }

  public Long getTicketId() {
    return ticketId;
  }
}
