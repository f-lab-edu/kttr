package com.crs.kttr.product.ticket.persistence;

import com.crs.kttr.product.ticket.model.TrainTicket;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface TrainTicketRepository extends JpaRepository<TrainTicket, Long> {
  @Lock(LockModeType.OPTIMISTIC)
  @Override
  Optional<TrainTicket> findById(Long id);
}
