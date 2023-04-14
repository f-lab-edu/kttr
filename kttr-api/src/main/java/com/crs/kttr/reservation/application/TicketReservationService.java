package com.crs.kttr.reservation.application;

import com.crs.kttr.reservation.domain.ReservationDetails;

public interface TicketReservationService {
  String issue(Long memberId, Long ticketId);

  ReservationDetails findReservationDetail(String code);
}
