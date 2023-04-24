package com.crs.kttr.reservation.application;

import com.crs.kttr.global.ServerExceptionDefinedReason;
import com.crs.kttr.member.application.MemberFindService;
import com.crs.kttr.member.exception.MemberNotFoundException;
import com.crs.kttr.member.model.Member;
import com.crs.kttr.product.ticket.application.TrainTicketService;
import com.crs.kttr.product.ticket.exception.TrainTicketNotFoundException;
import com.crs.kttr.product.ticket.model.TrainTicket;
import com.crs.kttr.reservation.model.ReservationDetails;
import com.crs.kttr.reservation.persistence.ReservationDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketReservationService {
  private final MemberFindService memberService;

  private final TrainTicketService ticketService;

  private final ReservationDetailsRepository repo;

  public String reserve(Long memberId, Long ticketId) {
    final Member member = memberService.findById(memberId)
      .orElseThrow(() -> new MemberNotFoundException(ServerExceptionDefinedReason.NOT_FOUND_RESOURCE));

    final TrainTicket ticket = ticketService.findBy(ticketId)
      .orElseThrow(() -> new TrainTicketNotFoundException(ServerExceptionDefinedReason.NOT_FOUND_RESOURCE));

    ticket.issue();

    final ReservationDetails stored = repo.save(new ReservationDetails(member.getId(), ticket.getId()));

    return stored.getReservationCode();
  }
}
