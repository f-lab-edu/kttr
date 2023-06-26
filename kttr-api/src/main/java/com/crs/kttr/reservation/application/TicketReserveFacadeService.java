package com.crs.kttr.reservation.application;

import com.crs.kttr.aop.RedisLockTransaction;
import com.crs.kttr.reservation.vo.ReservationCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketReserveFacadeService {
  final TicketReserveValidate validService;
  final TicketReserve reservationService;

  @RedisLockTransaction
  public String reserve(Long memberId, Long ticketId) {
    ReservationCommand command = validService.isValid(memberId, ticketId);

    return reservationService.reserve(command.getMember().getId(), command.getTicket());
  }
}
