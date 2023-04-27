package com.crs.kttr.reservation.thread.race.condition;

import com.crs.kttr.product.ticket.model.TrainTicketPOJO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.IntSummaryStatistics;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class TrainTicketIssueTest {

  @Test
  @DisplayName("티켓 발행 테스트")
  void test() throws InterruptedException, BrokenBarrierException {
    ExecutorService es = Executors.newFixedThreadPool(4);

    CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    IntStream.range(0, 4).forEach(e -> {
      es.submit(() -> {
        try {
          cyclicBarrier.await();
        } catch (InterruptedException ex) {
          throw new RuntimeException(ex);
        } catch (BrokenBarrierException ex) {
          throw new RuntimeException(ex);
        }

        TrainTicketPOJO.issue();
      });
    });

    Thread.sleep(5000);
    cyclicBarrier.await();

    es.shutdown();
    System.out.println("ticket = " + TrainTicketPOJO.issueCount);
  }
}
