package com.crs.kttr.reservation.application;

import com.crs.kttr.global.ServerExceptionDefinedReason;
import com.crs.kttr.global.service.RedisLockRunner;
import com.crs.kttr.member.application.MemberFindService;
import com.crs.kttr.member.exception.MemberNotFoundException;
import com.crs.kttr.member.model.Member;
import com.crs.kttr.product.ticket.application.TrainTicketService;
import com.crs.kttr.product.ticket.exception.TrainTicketNotFoundException;
import com.crs.kttr.product.ticket.model.TrainTicket;
import com.crs.kttr.reservation.exception.AlreadyHasReservation;
import com.crs.kttr.reservation.service.ReservationDetailsCRUDService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TicketReservationService {
  private static final String START_TICKET_ISSUE_JOB_LOCK_NAME_PREFIX = "ticket:";

  private final MemberFindService memberService;

  private final TrainTicketService ticketService;

  private final ReservationDetailsCRUDService reserveService;

  private final RedisLockRunner runner;

  public TicketReservationService(MemberFindService memberService,
                                  TrainTicketService ticketService,
                                  ReservationDetailsCRUDService reserveService,
                                  RedissonClient client) {
    this.memberService = memberService;
    this.ticketService = ticketService;
    this.reserveService = reserveService;
    this.runner = new RedisLockRunner(client);
  }

  @Transactional
  public String reserve(Long memberId, Long ticketId) {
    final Member member = memberService.findById(memberId)
      .orElseThrow(() -> new MemberNotFoundException(ServerExceptionDefinedReason.NOT_FOUND_RESOURCE));

    final TrainTicket ticket = ticketService.findBy(ticketId)
      .orElseThrow(() -> new TrainTicketNotFoundException(ServerExceptionDefinedReason.NOT_FOUND_RESOURCE));

    final Boolean existsReservation = reserveService.existsReservation(member.getId(), ticketId);
    if (existsReservation) {
      throw new AlreadyHasReservation(ServerExceptionDefinedReason.ALREADY_RESERVATION);
    }

    final String lockName = String.format("%s%d", START_TICKET_ISSUE_JOB_LOCK_NAME_PREFIX, ticket.getId());
    runner.run(lockName, () -> ticket.issue());

    return reserveService.save(member.getId(), ticket.getId()).getReservationCode();
  }
}
