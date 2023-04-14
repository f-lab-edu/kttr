package com.crs.kttr.reservation.application;

import com.crs.kttr.member.domain.TrainMember;
import com.crs.kttr.member.persistence.TrainMemberRepository;
import com.crs.kttr.member.persistence.TrainMemberRepositoryImpl;
import com.crs.kttr.product.ticket.domain.TrainTicket;
import com.crs.kttr.product.ticket.persistence.TrainTicketRepository;
import com.crs.kttr.product.ticket.persistence.TrainTicketRepositoryImpl;
import com.crs.kttr.reservation.domain.ReservationDetails;
import com.crs.kttr.reservation.persistence.ReservationDetailsRepository;
import com.crs.kttr.reservation.persistence.ReservationDetailsRepositoryImpl;

public class TicketReservationServiceImpl implements TicketReservationService {
  private final TrainTicketRepository ticketRepo = new TrainTicketRepositoryImpl();
  private final TrainMemberRepository memberRepo = new TrainMemberRepositoryImpl();

  private final ReservationDetailsRepository reservationRepo = new ReservationDetailsRepositoryImpl();

  @Override
  public String issue(Long memberId, Long ticketId) {
    final TrainMember member = memberRepo.findMember(memberId);

    final TrainTicket trainTicket = ticketRepo.findTicket(ticketId);
    trainTicket.issue();


    final ReservationDetails details = new ReservationDetails(member.getId(), trainTicket.getId());
    reservationRepo.save(details);

    final ReservationDetails findDetails = reservationRepo.findReservationDetails(details.getReservationCode());

    return findDetails.getReservationCode();
  }

  @Override
  public ReservationDetails findReservationDetail(String code) {
    return reservationRepo.findReservationDetails(code);
  }
}
