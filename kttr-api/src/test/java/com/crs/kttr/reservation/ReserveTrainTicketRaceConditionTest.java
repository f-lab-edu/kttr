package com.crs.kttr.reservation;

import com.crs.kttr.product.ticket.model.TrainTicket;
import com.crs.kttr.product.ticket.persistence.TrainTicketRepository;
import com.crs.kttr.reservation.application.TicketReserveFacadeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@SpringBootTest
@AutoConfigureMockMvc
public class ReserveTrainTicketRaceConditionTest {
  private static final Integer POOL_SIZE = 100;
  private static final Integer PARTIES = 100;
  private static final Integer USER_COUNT = 10000;

  @Autowired
  private TicketReserveFacadeService service;

  @Autowired
  private TrainTicketRepository ticketRepository;


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

    List<TrainTicket> tickets = ticketRepository.findAll();
    if (!tickets.isEmpty()) {
      Assertions.assertEquals(20, tickets.get(0).getIssueQuantity());
    }
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
