package com.crs.kttr.reservation.persistence;

import com.crs.kttr.reservation.model.ReservationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDetailsRepository extends JpaRepository<ReservationDetails, Long> {
}
