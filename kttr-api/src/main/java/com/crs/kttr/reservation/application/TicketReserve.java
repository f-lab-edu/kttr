package com.crs.kttr.reservation.application;

import com.crs.kttr.aop.RedisLockTransaction;
import com.crs.kttr.product.ticket.model.TrainTicket;
import com.crs.kttr.product.ticket.persistence.TrainTicketRepository;
import com.crs.kttr.reservation.model.ReservationDetails;
import com.crs.kttr.reservation.service.ReservationDetailsCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketReserve {
  private final ReservationDetailsCRUDService reserveService;

  private final TrainTicketRepository repo;

  public String reserve(Long memberId, TrainTicket ticket) {
    ticket.issue();

    ReservationDetails details = reserveService.save(memberId, ticket.getId());

    repo.save(ticket);
    return details.getReservationCode();
  }
}
