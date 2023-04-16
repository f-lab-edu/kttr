package com.crs.kttr.product;

import com.crs.kttr.product.ticket.application.TrainTicketService;
import com.crs.kttr.product.ticket.application.TrainTicketServiceImpl;
import com.crs.kttr.product.ticket.domain.TrainTicket;
import com.crs.kttr.product.ticket.exception.AlreadyRegisteredTrainTicketException;
import com.crs.kttr.product.ticket.exception.TrainTicketNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TrainTicketCRUDTest {
  TrainTicketService ticketService;

  @BeforeEach
  void setUp() {
    ticketService = new TrainTicketServiceImpl();
  }

  @Test
  @DisplayName("id가 null인 경우")
  void isnullId() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new TrainTicket(null, "티켓1", 10);
    });
  }


  @Test
  @DisplayName("id가 0인 경우")
  void isZeroId() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new TrainTicket(0L, "티켓1", 10);
    });
  }

  @Test
  @DisplayName("id가 음수인 경우")
  void isNegativeId() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new TrainTicket(-1L, "티켓1", 10);
    });
  }

  @Test
  @DisplayName("티켓 이름이 null인 경우")
  void isnullName() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new TrainTicket(1L, null, 10);
    });
  }

  @Test
  @DisplayName("티켓 이름이 공백 경우")
  void isEmptyName() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new TrainTicket(1L, "", 10);
    });
  }

  @Test
  @DisplayName("수량이 null인 경우")
  void isnullMaxQuantity() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new TrainTicket(1L, "티켓1", null);
    });
  }


  @Test
  @DisplayName("수량이 0인 경우")
  void isZeroMaxQuantity() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new TrainTicket(1L, "티켓1", 0);
    });
  }

  @Test
  @DisplayName("수량이 음수인 경우")
  void isNegativeMaxQuantity() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new TrainTicket(1L, "티켓1", -1);
    });
  }

  @Test
  @DisplayName("기차 티켓 등록")
  void register() {
    final TrainTicket ticket = new TrainTicket(1L, "티켓1", 10);

    ticketService.save(ticket);
  }

  @Test
  @DisplayName("기차 티켓 중복 등록 시 오류 처리")
  void duplicateRegister() {
    final TrainTicket ticketA = new TrainTicket(2L, "티켓1", 10);
    final TrainTicket ticketB = new TrainTicket(2L, "티켓1", 10);

    ticketService.save(ticketA);
    Assertions.assertThrows(AlreadyRegisteredTrainTicketException.class, () -> {
      ticketService.save(ticketB);
    });
  }

  @Test
  @DisplayName("기차 티켓 조회")
  void findTrainTicket() {
    ticketService.save(new TrainTicket(1L, "티켓A", 10));

    final TrainTicket trainTicket = ticketService.findTrainTicket(1L);

    Assertions.assertEquals(trainTicket.getId(), 1L);
  }


  @Test
  @DisplayName("기차 티켓 조회 시 없을 경우")
  void notFoundTrainTicket() {
    Assertions.assertThrows(TrainTicketNotFoundException.class, () -> {
      ticketService.findTrainTicket(0L);
    });
  }
}
