package com.crs.kttr.product.ticket.application;

import com.crs.kttr.product.ticket.domain.TrainTicket;

public interface TrainTicketService {

  void save(TrainTicket trainTicket);

  TrainTicket findTrainTicket(Long ticketId);
}
