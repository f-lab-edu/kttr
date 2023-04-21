package com.crs.kttr.reservation;

import com.crs.kttr.member.application.MemberFindService;
import com.crs.kttr.member.exception.MemberNotFoundException;
import com.crs.kttr.member.model.Member;
import com.crs.kttr.product.ticket.application.TrainTicketService;
import com.crs.kttr.product.ticket.exception.TrainTicketNotFoundException;
import com.crs.kttr.product.ticket.model.TrainTicket;
import com.crs.kttr.reservation.application.TicketReservationService;
import com.crs.kttr.reservation.controller.dto.TrainTicketReserveRequest;
import com.crs.kttr.reservation.model.ReservationDetails;
import com.crs.kttr.reservation.persistence.ReservationDetailsRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TicketReservationTest {

  @InjectMocks
  private TicketReservationService service;

  @Mock
  private MemberFindService memberService;

  @Mock
  private TrainTicketService ticketService;

  @Mock
  private ReservationDetailsRepository repo;

  ValidatorFactory factory;
  Validator validator;

  @BeforeEach
  void setUp() {
    factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("티켓 요청 시 멤버 id가 null인 경우")
  void isNullMemberId() {
    // given
    final TrainTicketReserveRequest req = new TrainTicketReserveRequest(  null, 1L);

    // when
    Set<ConstraintViolation<TrainTicketReserveRequest>> constraintViolations = validator.validate(req);

    // then
    Assertions.assertEquals(1, constraintViolations.size());
  }

  @Test
  @DisplayName("티켓 요청 시 티켓 id가 null인 경우")
  void isNullTicketId() {
    // given
    final TrainTicketReserveRequest req = new TrainTicketReserveRequest(  1L, null);

    // when
    Set<ConstraintViolation<TrainTicketReserveRequest>> constraintViolations = validator.validate(req);

    // then
    Assertions.assertEquals(1, constraintViolations.size());
  }

  @Test
  @DisplayName("기차 티켓 예약")
  void reserve() {
    // given
    final TrainTicket ticket = new TrainTicket("티켓1", 10);
    final Member member = new Member("test@test.co.kr", "encryted-password", "test@test.co.kr", "010-0000-0000");
    final ReservationDetails reservationDetails = new ReservationDetails(1L, 1L);

    ReflectionTestUtils.setField(reservationDetails, "id", 1L);

    // mocking
    given(memberService.findById(any())).willReturn(Optional.ofNullable(member));
    given(ticketService.findBy(any())).willReturn(Optional.ofNullable(ticket));
    given(repo.save(any())).willReturn(reservationDetails);

    // when
    final String reservationCode = service.reserve(1L, 1L);

    // then
    assertEquals(reservationDetails.getReservationCode(), reservationCode);
  }

  @Test
  @DisplayName("기차 티켓 예약 시 사용자 없는 경우")
  void notFoundMember() {
    // when & then
    Assertions.assertThrows(MemberNotFoundException.class,
      () -> service.reserve(0L, 1L));
  }

  @Test
  @DisplayName("기차 티켓 예약 시 티켓이 없는 경우")
  void notFoundTicket() {
    // given
    final Member member = new Member("test@test.co.kr", "encryted-password", "test@test.co.kr", "010-0000-0000");

    // mocking
    given(memberService.findById(any())).willReturn(Optional.ofNullable(member));

    // when & then
    Assertions.assertThrows(TrainTicketNotFoundException.class,
      () -> service.reserve(1L, 1L));
  }
}
