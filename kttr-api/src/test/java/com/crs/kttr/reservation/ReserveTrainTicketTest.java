package com.crs.kttr.reservation;

import com.crs.kttr.reservation.application.TicketReserve;
import com.crs.kttr.reservation.controller.dto.TrainTicketReserveRequest;
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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ReserveTrainTicketTest {

  @InjectMocks
  private TicketReserve service;

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
}
