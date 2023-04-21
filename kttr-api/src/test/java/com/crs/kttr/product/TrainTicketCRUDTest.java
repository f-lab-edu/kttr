package com.crs.kttr.product;

import com.crs.kttr.product.ticket.application.TrainTicketService;
import com.crs.kttr.product.ticket.controller.dto.TrainTicketRegisterRequest;
import com.crs.kttr.product.ticket.model.TrainTicket;
import com.crs.kttr.product.ticket.persistence.TrainTicketRepository;
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
public class TrainTicketCRUDTest {
  @InjectMocks
  private TrainTicketService ticketService;

  @Mock
  private TrainTicketRepository repo;

  ValidatorFactory factory;
  Validator validator;

  @BeforeEach
  void setUp() {
    factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("티켓 이름이 null인 경우")
  void isnullName() {
    // given
    TrainTicketRegisterRequest req = new TrainTicketRegisterRequest(null, 10);;

    // when
    Set<ConstraintViolation<TrainTicketRegisterRequest>> constraintViolations = validator.validate(req);

    // then
    Assertions.assertEquals(1, constraintViolations.size());
  }

  @Test
  @DisplayName("티켓 이름이 공백 경우")
  void isEmptyName() {
    // given
    TrainTicketRegisterRequest req = new TrainTicketRegisterRequest("", 10);

    // when
    Set<ConstraintViolation<TrainTicketRegisterRequest>> constraintViolations = validator.validate(req);

    // then
    Assertions.assertEquals(1, constraintViolations.size());
  }

  @Test
  @DisplayName("수량이 null인 경우")
  void isnullMaxQuantity() {
    // given
    TrainTicketRegisterRequest req = new TrainTicketRegisterRequest("티켓", null);
    // when
    Set<ConstraintViolation<TrainTicketRegisterRequest>> constraintViolations = validator.validate(req);

    // then
    Assertions.assertEquals(1, constraintViolations.size());
  }


  @Test
  @DisplayName("수량이 0인 경우")
  void isZeroMaxQuantity() {
    // given
    TrainTicketRegisterRequest req = new TrainTicketRegisterRequest("티켓", 0);
    // when
    Set<ConstraintViolation<TrainTicketRegisterRequest>> constraintViolations = validator.validate(req);

    // then
    Assertions.assertEquals(1, constraintViolations.size());
  }

  @Test
  @DisplayName("수량이 음수인 경우")
  void isNegativeMaxQuantity() {
    // given
    TrainTicketRegisterRequest req = new TrainTicketRegisterRequest("티켓", -1);
    // when
    Set<ConstraintViolation<TrainTicketRegisterRequest>> constraintViolations = validator.validate(req);

    // then
    Assertions.assertEquals(1, constraintViolations.size());
  }

  @Test
  @DisplayName("기차 티켓 등록")
  void register() {
    // given
    final TrainTicketRegisterRequest req = new TrainTicketRegisterRequest("티켓1", 10);
    final TrainTicket ticket = new TrainTicket("티켓1", 10);

    Long fakeTicketId = 1L;
    ReflectionTestUtils.setField(ticket, "id", fakeTicketId);

    // mocking
    given(repo.save(any())).willReturn(ticket);
    given(repo.findById(fakeTicketId)).willReturn(Optional.ofNullable(ticket));

    // when
    final TrainTicket stored = ticketService.register(req);

    // then
    final TrainTicket newTicket = repo.findById(stored.getId()).get();
    assertEquals(ticket.getId(), newTicket.getId());
  }

  @Test
  @DisplayName("기차 티켓 조회")
  void findTrainTicket() {
    // given
    final TrainTicket ticket = new TrainTicket("티켓1", 10);

    Long fakeTicketId = 1L;
    ReflectionTestUtils.setField(ticket, "id", fakeTicketId);

    // mocking
    given(repo.findById(fakeTicketId)).willReturn(Optional.ofNullable(ticket));


    final TrainTicket findTicket = repo.findById(1L).get();
    assertEquals(ticket.getId(), findTicket.getId());
  }


  @Test
  @DisplayName("기차 티켓 조회 시 없을 경우")
  void notFoundTrainTicket() {
    Assertions.assertEquals(false, repo.findById(0L).isPresent());
  }
}
