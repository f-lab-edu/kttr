package com.crs.kttr.reservation.application;

import com.crs.kttr.global.ServerExceptionDefinedReason;
import com.crs.kttr.member.application.MemberFindService;
import com.crs.kttr.member.exception.MemberNotFoundException;
import com.crs.kttr.member.model.Member;
import com.crs.kttr.product.ticket.application.TrainTicketService;
import com.crs.kttr.product.ticket.exception.TrainTicketNotFoundException;
import com.crs.kttr.product.ticket.model.TrainTicket;
import com.crs.kttr.reservation.exception.AlreadyHasReservation;
import com.crs.kttr.reservation.model.ReservationDetails;
import com.crs.kttr.reservation.persistence.ReservationDetailsRepository;
import com.crs.kttr.reservation.service.ReservationDetailsCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketReservationService {
  private final MemberFindService memberService;

  private final TrainTicketService ticketService;

  private final ReservationDetailsCRUDService reserveService;

  @Transactional
  public String reserve(Long memberId, Long ticketId) {
    final Member member = memberService.findById(memberId)
      .orElseThrow(() -> new MemberNotFoundException(ServerExceptionDefinedReason.NOT_FOUND_RESOURCE));

    final TrainTicket ticket = ticketService.findBy(ticketId)
      .orElseThrow(() -> new TrainTicketNotFoundException(ServerExceptionDefinedReason.NOT_FOUND_RESOURCE));

    final Boolean existsReservation = reserveService.existsReservation(member.getId(), ticketId);
    if (existsReservation) {
      throw new AlreadyHasReservation(ServerExceptionDefinedReason.ALREADY_RESERVATION);
    }

    ticket.issue();

    return reserveService.save(member.getId(), ticket.getId()).getReservationCode();
  }
}
