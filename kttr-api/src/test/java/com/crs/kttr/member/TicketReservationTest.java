package com.crs.kttr.member;

import com.crs.kttr.member.application.TrainMemberService;
import com.crs.kttr.member.application.TrainMemberServiceImpl;
import com.crs.kttr.member.domain.TrainMember;
import com.crs.kttr.product.ticket.application.TrainTicketService;
import com.crs.kttr.product.ticket.application.TrainTicketServiceImpl;
import com.crs.kttr.product.ticket.domain.TrainTicket;
import com.crs.kttr.reservation.application.TicketReservationService;
import com.crs.kttr.reservation.application.TicketReservationServiceImpl;
import com.crs.kttr.reservation.domain.ReservationDetails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketReservationTest {
  TrainMemberService memberService = new TrainMemberServiceImpl();

  TrainTicketService ticketService = new TrainTicketServiceImpl();

  TicketReservationService reservationService = new TicketReservationServiceImpl();

  @Test
  void reservation() {
    // given
    TrainMember trainMember = new TrainMember(1L, "memberA");
    memberService.join(trainMember);
    final TrainMember findMember = memberService.findMember(trainMember.getId());

    final TrainTicket trainTicket = new TrainTicket(1L, "ticketA", 10);
    ticketService.save(trainTicket);
    final TrainTicket findTrainTicket = ticketService.findTrainTicket(trainTicket.getId());

    // when
    final String code = reservationService.issue(findMember.getId(), findTrainTicket.getId());
    final ReservationDetails reservationDetails = reservationService.findReservationDetail(code);

    // then
    Assertions.assertThat(code).isEqualTo(reservationDetails.getReservationCode());
  }
}
