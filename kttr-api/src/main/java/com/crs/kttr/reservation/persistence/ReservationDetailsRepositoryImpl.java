package com.crs.kttr.reservation.persistence;

import com.crs.kttr.reservation.domain.ReservationDetails;

import java.util.HashMap;
import java.util.Map;

public class ReservationDetailsRepositoryImpl implements ReservationDetailsRepository {

  private static Map<String, ReservationDetails> store = new HashMap<>();

  @Override
  public void save(ReservationDetails reservationDetails) {
    store.put(reservationDetails.getReservationCode(), reservationDetails);
  }

  @Override
  public ReservationDetails findReservationDetails(String reservationCode) {
    return store.get(reservationCode);
  }
}
