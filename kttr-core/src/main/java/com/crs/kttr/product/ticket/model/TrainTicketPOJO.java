package com.crs.kttr.product.ticket.model;

import lombok.Getter;

@Getter
public class TrainTicketPOJO {
  public static Integer issueCount = 0;

  private static String name = "티켓";

  public static void issue() {
    issueCount += 1;
  }
}
