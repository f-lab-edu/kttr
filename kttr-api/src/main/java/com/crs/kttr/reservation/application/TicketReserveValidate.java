package com.crs.kttr.reservation.application;

import com.crs.kttr.global.ServerExceptionDefinedReason;
import com.crs.kttr.member.application.MemberFindService;
import com.crs.kttr.member.exception.MemberNotFoundException;
import com.crs.kttr.member.model.Member;
import com.crs.kttr.product.ticket.application.TrainTicketService;
import com.crs.kttr.product.ticket.exception.TrainTicketNotFoundException;
import com.crs.kttr.product.ticket.model.TrainTicket;
import com.crs.kttr.reservation.exception.AlreadyHasReservation;
import com.crs.kttr.reservation.service.ReservationDetailsCRUDService;
import com.crs.kttr.reservation.vo.ReservationCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketReserveValidate {
  private final MemberFindService memberService;
  private final TrainTicketService ticketService;
  private final ReservationDetailsCRUDService reserveService;

  public ReservationCommand isValid(Long memberId, Long ticketId) {
    final Member member = memberService.find(memberId)
        .orElseThrow(() -> new MemberNotFoundException(ServerExceptionDefinedReason.NOT_FOUND_RESOURCE));

    final TrainTicket ticket = ticketService.find(ticketId)
        .orElseThrow(() -> new TrainTicketNotFoundException(ServerExceptionDefinedReason.NOT_FOUND_RESOURCE));

    final Boolean existsReservation = reserveService.existsReservation(member.getId(), ticketId);
    if (existsReservation) {
      throw new AlreadyHasReservation(ServerExceptionDefinedReason.ALREADY_RESERVATION);
    }

    return new ReservationCommand(member, ticket);
  }
}
