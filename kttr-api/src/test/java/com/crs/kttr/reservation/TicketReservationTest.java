package com.crs.kttr.reservation;

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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketReservationTest {
  TrainMemberService memberService = new TrainMemberServiceImpl();

  TrainTicketService ticketService = new TrainTicketServiceImpl();

  TicketReservationService reservationService = new TicketReservationServiceImpl();

  @Test
  @DisplayName("기차 티켓 예약")
  void reserve() {
    // given
    TrainMember trainMember = new TrainMember(1L, "memberA");
    memberService.join(trainMember);
    final TrainMember findMember = memberService.findMember(trainMember.getId());

    final TrainTicket trainTicket = new TrainTicket(1L, "ticketA", 10);
    ticketService.save(trainTicket);
    final TrainTicket findTrainTicket = ticketService.findTrainTicket(trainTicket.getId());

    // TODO : 저장소 문제로 테스트 다시 고려해야함..
    // when
//    final String code = reservationService.reserve(findMember.getId(), findTrainTicket.getId());
//    final ReservationDetails reservationDetails = reservationService.findReservationDetail(code);
//
//    // then
//    Assertions.assertThat(code).isEqualTo(reservationDetails.getReservationCode());
  }
}
