package com.crs.kttr.reservation;

import com.crs.kttr.product.ticket.model.Ticket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketIssueTest {
  @Test
  @DisplayName(value = "티켓 발행 부하 테스트")
  public void issue() throws InterruptedException {
    // 자원
    final Ticket ticket = new Ticket();

    // Thread1
    Thread thread1 = new Thread(
      () -> {
        for (int i = 0; i < 100; i++) {
          ticket.issue();
          System.out.println("threadId = " + Thread.currentThread().getId() + ", ticket = " + ticket.toString());
        }
      }
    );

    // Thread2
    Thread thread2 = new Thread(
      () -> {
        for (int i = 0; i < 100; i++) {
          ticket.issue();

          System.out.println("threadId = " + Thread.currentThread().getId() + ", ticket = " + ticket.toString());
        }
      }
    );

    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    System.out.println("ticket.toString() = " + ticket.toString());
  }
}
