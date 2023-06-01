package com.crs.kttr.reservation.application;

import com.crs.kttr.global.ServerExceptionDefinedReason;
import com.crs.kttr.global.service.RedisLockRunner;
import com.crs.kttr.member.application.MemberFindService;
import com.crs.kttr.member.exception.MemberNotFoundException;
import com.crs.kttr.member.model.Member;
import com.crs.kttr.product.exception.TrainTicketOutOfStockException;
import com.crs.kttr.product.ticket.application.TrainTicketService;
import com.crs.kttr.product.ticket.exception.TrainTicketNotFoundException;
import com.crs.kttr.product.ticket.model.Stock;
import com.crs.kttr.product.ticket.model.TrainTicket;
import com.crs.kttr.reservation.exception.AlreadyHasReservation;
import com.crs.kttr.reservation.service.ReservationDetailsCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class TicketReservationService {
  private static final String START_TICKET_ISSUE_JOB_LOCK_NAME_PREFIX = "ticket:";

  private final MemberFindService memberService;

  private final TrainTicketService ticketService;

  private final ReservationDetailsCRUDService reserveService;

  private final RedisLockRunner runner;

  @Transactional
  public String reserve(Long memberId, Long ticketId) {
    final String lockName = String.format("%s%d", START_TICKET_ISSUE_JOB_LOCK_NAME_PREFIX, ticketId);

    AtomicReference<String> reservationCode = new AtomicReference<>("");
    runner.run(lockName, () -> {
      final Member member = memberService.find(memberId)
        .orElseThrow(() -> new MemberNotFoundException(ServerExceptionDefinedReason.NOT_FOUND_RESOURCE));

      final TrainTicket ticket = ticketService.find(ticketId)
        .orElseThrow(() -> new TrainTicketNotFoundException(ServerExceptionDefinedReason.NOT_FOUND_RESOURCE));

      final Boolean existsReservation = reserveService.existsReservation(member.getId(), ticketId);
      if (existsReservation) {
        throw new AlreadyHasReservation(ServerExceptionDefinedReason.ALREADY_RESERVATION);
      }

      final int stock = runner.currentStock("stock");
      if(stock <= 0){
        throw new TrainTicketOutOfStockException(ServerExceptionDefinedReason.TRAIN_TICKET_OUT_OF_STOCK);
      }

      reservationCode.set(reserveService.save(member.getId(), ticket.getId()).getReservationCode());
      runner.setStock("stock", stock - 1);
    });

    return reservationCode.get();
  }

  public void setStock(String stockKey, int amount) {
    runner.setStock(stockKey, amount);
  }
}
