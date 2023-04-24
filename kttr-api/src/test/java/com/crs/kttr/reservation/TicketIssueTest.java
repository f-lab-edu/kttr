package com.crs.kttr.reservation;

import com.crs.kttr.product.ticket.model.TrainTicket;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class TicketIssueTest {
  private static final Integer USER_COUNT = 10000;
  private static final Integer THREAD_POOL_SIZE = 2000;

  private static final Integer THREAD_PARTIES = 50;
  @Test
  @DisplayName(value = "티켓 발행 부하 테스트")
  public void issue() {
    // given
    final TrainTicket ticket = new TrainTicket("테스트", 100);

    ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    Set<Integer> store = Sets.newHashSet();

    CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_PARTIES, () -> {
      ticket.issue();

      store.add(ticket.getIssueQuantity());
    });

    IntStream.range(0, USER_COUNT).forEach( e -> {
      UserThread thread = new UserThread(cyclicBarrier, "사용자_" + e);
      executor.execute(thread);
    });

    executor.shutdown();
    while (!executor.isTerminated()) {
    }

    for (int i = 1; i <= ticket.getMaxQuantity(); i++) {
      if (!store.contains(i)) {
        System.out.println("i = " + i);
      }
    }
    System.out.println("ticket.toString() = " + ticket.toString());

    Assertions.assertEquals(Boolean.TRUE, store.size() == ticket.getMaxQuantity());
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
