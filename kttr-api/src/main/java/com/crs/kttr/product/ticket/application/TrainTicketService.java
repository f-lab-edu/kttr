package com.crs.kttr.product.ticket.application;

import com.crs.kttr.product.ticket.controller.dto.TrainTicketRegisterRequest;
import com.crs.kttr.product.ticket.model.TrainTicket;
import com.crs.kttr.product.ticket.persistence.TrainTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainTicketService {

  private final TrainTicketRepository repo;

  public Optional<TrainTicket> findBy(Long ticketId) {
    return repo.findById(ticketId);
  }

  public TrainTicket register(final TrainTicketRegisterRequest req) {
    return repo.save(new TrainTicket(req.getName(), req.getMaxQuantity()));
  }
}