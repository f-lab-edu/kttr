package com.crs.kttr.product.ticket.persistence;

import com.crs.kttr.product.ticket.domain.TrainTicket;

public interface TrainTicketRepository {
  void save(TrainTicket ticket);
  TrainTicket findTicket(Long ticketId);
}
