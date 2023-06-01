package com.crs.kttr.reservation;

import com.crs.kttr.member.exception.MemberNotFoundException;
import com.crs.kttr.member.model.Member;
import com.crs.kttr.product.ticket.exception.TrainTicketNotFoundException;
import com.crs.kttr.product.ticket.model.Stock;
import com.crs.kttr.reservation.application.TicketReservationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class ReserveTrainTicketRaceConditionTest {
  private static final Integer POOL_SIZE = 100;
  private static final Integer PARTIES = 100;
  private static final Integer USER_COUNT = 10000;

  @Autowired
  private TicketReservationService service;

  @BeforeEach
  void setUp() {
    final Stock ticket = new Stock("ticket", 1L, 20);

    service.setStock("stock", ticket.getAmount());
  }

  @Test
  @DisplayName("락 사용하여 기차 예약 성공")
  void reserve() throws InterruptedException, BrokenBarrierException {
    ExecutorService es = Executors.newFixedThreadPool(POOL_SIZE);

    CyclicBarrier cyclicBarrier = new CyclicBarrier(PARTIES);
    IntStream.range(0, USER_COUNT).forEach(e -> {
      es.submit(() -> {
        try {
          cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException  ex) {
          throw new RuntimeException(ex);
        }

        service.reserve(Long.valueOf(e), 1L);
      });
    });

    Thread.sleep(50);
    cyclicBarrier.await();

    es.shutdown();
  }

  @Test
  @DisplayName("기차 티켓 예약 시 사용자 없는 경우")
  void notFoundMember() {
    // when & then
    Assertions.assertThrows(RuntimeException.class,
      () -> service.reserve(0L, 1L));
  }

  @Test
  @DisplayName("기차 티켓 예약 시 티켓이 없는 경우")
  void notFoundTicket() {
    // when & then
    Assertions.assertThrows(RuntimeException.class,
      () -> service.reserve(1L, 0L));
  }
}
