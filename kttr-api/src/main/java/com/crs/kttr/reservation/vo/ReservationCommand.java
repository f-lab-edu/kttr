package com.crs.kttr.reservation.vo;

import com.crs.kttr.member.model.Member;
import com.crs.kttr.product.ticket.model.TrainTicket;
import lombok.Value;

@Value
public class ReservationCommand {
  private final Member member;

  private final TrainTicket ticket;
}
