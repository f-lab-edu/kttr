package com.crs.kttr.product.ticket.application;

import com.crs.kttr.product.ticket.domain.TrainTicket;
import com.crs.kttr.product.ticket.exception.AlreadyRegisteredTrainTicketException;
import com.crs.kttr.product.ticket.exception.TrainTicketNotFoundException;
import com.crs.kttr.product.ticket.persistence.TrainTicketRepository;
import com.crs.kttr.product.ticket.persistence.TrainTicketRepositoryImpl;

public class TrainTicketServiceImpl implements TrainTicketService {
  final TrainTicketRepository repo = new TrainTicketRepositoryImpl();

  @Override
  public void save(TrainTicket trainTicket) {
    if (existById(trainTicket.getId())) {
      throw new AlreadyRegisteredTrainTicketException("이미 등록된 티켓입니다.");
    }

    repo.save(trainTicket);
  }

  private boolean existById(Long ticketId) {
    return repo.findTicket(ticketId) != null;
  }

  @Override
  public TrainTicket findTrainTicket(Long ticketId) {
    final TrainTicket ticket = repo.findTicket(ticketId);
    if (ticket == null) {
      throw new TrainTicketNotFoundException("티켓을 조회할 수 없습니다.");
    }

    return ticket;
  }
}
