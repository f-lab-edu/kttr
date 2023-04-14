package com.crs.kttr.product.ticket.application;

import com.crs.kttr.product.ticket.domain.TrainTicket;
import com.crs.kttr.product.ticket.persistence.TrainTicketRepository;
import com.crs.kttr.product.ticket.persistence.TrainTicketRepositoryImpl;

public class TrainTicketServiceImpl implements TrainTicketService {
  final TrainTicketRepository repo = new TrainTicketRepositoryImpl();

  @Override
  public void save(TrainTicket trainTicket) {
    repo.save(trainTicket);
  }

  @Override
  public TrainTicket findTrainTicket(Long ticketId) {
    return repo.findTicket(ticketId);
  }
}
