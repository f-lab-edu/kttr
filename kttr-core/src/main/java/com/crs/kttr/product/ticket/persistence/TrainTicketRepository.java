package com.crs.kttr.product.ticket.persistence;

import com.crs.kttr.product.ticket.model.TrainTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainTicketRepository extends JpaRepository<TrainTicket, Long> {
}
