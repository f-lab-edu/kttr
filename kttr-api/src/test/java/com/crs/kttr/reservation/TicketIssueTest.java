package com.crs.kttr.reservation;

import com.crs.kttr.product.ticket.model.Ticket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketIssueTest {
  @Test
  @DisplayName(value = "티켓 발행 부하 테스트")
  public void issue() throws InterruptedException {
    final Ticket ticket = new Ticket();

    for (int i = 0; i < 200; i++) {
      Thread thread1 = new Thread(
        () -> ticket.issue()
      );
      Thread thread2 = new Thread(
        () -> ticket.issue()
      );
      Thread.sleep(100);
      thread1.start();
      thread2.start();
      System.out.println("threadId = " + thread1.getId() + ", ticket = " + ticket.toString());
      System.out.println("threadId = " + thread2.getId() + ", ticket = " + ticket.toString());
    }
  }
}
