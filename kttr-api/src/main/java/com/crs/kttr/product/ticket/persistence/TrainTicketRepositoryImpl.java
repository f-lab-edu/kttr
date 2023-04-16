package com.crs.kttr.product.ticket.persistence;

import com.crs.kttr.product.ticket.domain.TrainTicket;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TrainTicketRepositoryImpl implements TrainTicketRepository {
  private Map<Long, TrainTicket> store = new ConcurrentHashMap<>();

  @Override
  public void save(TrainTicket ticket) {
    store.put(ticket.getId(), ticket);
  }

  @Override
  public TrainTicket findTicket(Long ticketId) {
    return store.get(ticketId);
  }
}
