package com.crs.kttr.reservation;

import com.crs.kttr.product.ticket.model.TrainTicket;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class TicketIssueTest {
  @Test
  @DisplayName(value = "티켓 발행 부하 테스트")
  public void issue() throws InterruptedException {
    // 자원
    final TrainTicket ticket = new TrainTicket("테스트", 2000);

    final Set<Integer> store = Sets.newHashSet();

    // Thread1
    Thread thread1 = new Thread(
      () -> {
        for (int i = 0; i < 1000; i++) {
          final Integer issued = ticket.issue();
          store.add(issued);
          System.out.println("threadId = " + Thread.currentThread().getId() + ", ticket = " + issued);
        }
      }
    );

    // Thread2
    Thread thread2 = new Thread(
      () -> {
        for (int i = 0; i < 1000; i++) {
          final Integer issued = ticket.issue();
          store.add(issued);
          System.out.println("threadId = " + Thread.currentThread().getId() + ", ticket = " + issued);
        }
      }
    );

    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    System.out.println("ticket.toString() = " + ticket.toString());
    for (int i = 1; i <= 2000; i++) {
      if (!store.contains(i)) {
        System.out.println("i = " + i);
      }
    }

    Assertions.assertEquals(Boolean.TRUE, store.size() < 2000);
  }
}
