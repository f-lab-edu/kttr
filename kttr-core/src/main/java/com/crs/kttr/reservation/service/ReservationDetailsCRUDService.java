package com.crs.kttr.reservation.service;

import com.crs.kttr.reservation.model.ReservationDetails;
import com.crs.kttr.reservation.persistence.ReservationDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationDetailsCRUDService {
  private final ReservationDetailsRepository repo;

  public ReservationDetails save(Long memberId, Long ticketId) {
    return repo.save(new ReservationDetails(memberId, ticketId));
  }

  public Boolean existsReservation(Long memberId, Long ticketId) {
    return Boolean.FALSE;
  }
}
