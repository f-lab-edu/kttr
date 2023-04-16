package com.crs.kttr.reservation.persistence;

import com.crs.kttr.reservation.domain.ReservationDetails;

public interface ReservationDetailsRepository {
  void save(ReservationDetails reservationDetails);

  ReservationDetails findReservationDetails(String reservationCode);

}
