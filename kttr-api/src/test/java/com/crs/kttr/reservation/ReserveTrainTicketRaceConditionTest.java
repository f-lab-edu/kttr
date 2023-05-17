package com.crs.kttr.reservation;

import com.crs.kttr.product.ticket.model.Stock;
import com.crs.kttr.reservation.application.TicketReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

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
  private TicketReservationService service;

  @BeforeEach
  void setUp() {
    final Stock ticket = new Stock("ticket", 1L, 20);

    service.setStock("stock", ticket.getAmount());
  }

  @Test
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
}
