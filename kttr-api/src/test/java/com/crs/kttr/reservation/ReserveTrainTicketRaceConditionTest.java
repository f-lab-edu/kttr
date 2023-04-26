package com.crs.kttr.reservation;

import com.crs.kttr.product.ticket.application.TrainTicketService;
import com.crs.kttr.product.ticket.model.TrainTicket;
import com.crs.kttr.reservation.application.TicketReservationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;
import java.util.stream.IntStream;

@SpringBootTest
@AutoConfigureMockMvc
public class ReserveTrainTicketRaceConditionTest {
  private static final ExecutorService executorService =
    Executors.newFixedThreadPool(100);

  private static final Integer THREAD_POOL_SIZE = 100;
  private static final Integer THREAD_PARTIES = 30;
  private static final Integer USER_COUNT = 90;

  @Autowired
  private TicketReservationService service;

  @Autowired
  private TrainTicketService ticketService;

  @Test
  @DisplayName("레이스 컨디션_티켓 예약")
  void reserve_with_race_condition() throws InterruptedException {

    ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_PARTIES, () -> {
      // when
      service.reserve(1L, 1L);
    });

    IntStream.range(0, USER_COUNT).forEach(e -> {
      UserThread thread = new UserThread(cyclicBarrier, "사용자_" + e);
      executor.execute(thread);
    });

    executor.shutdown();
    while (!executor.isTerminated()) {
    }

    CountDownLatch latch = new CountDownLatch(100);
    for (int i=0; i < 100; i++) {
      executorService.execute(() -> {
        service.reserve(1L, 3L);
        latch.countDown();
      });
    }
    latch.await();

    final TrainTicket ticket = ticketService.findBy(3L).get();

    Assertions.assertEquals(20, ticket.getIssueQuantity());
  }

  static class UserThread implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private String threadName;

    public UserThread(CyclicBarrier cyclicBarrier, String threadName) {
      this.cyclicBarrier = cyclicBarrier;
      this.threadName = threadName;
    }

    @Override
    public void run() {
      try {
        System.out.println(threadName);
        Thread.sleep(1000);
        cyclicBarrier.await();
      } catch (InterruptedException | BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }
}
