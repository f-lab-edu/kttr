package com.crs.kttr.reservation.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "reservation_details")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "id")
public class ReservationDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String reservationCode;

  private Long memberId;

  private Long ticketId;

  public ReservationDetails(Long memberId, Long ticketId) {
    this.reservationCode = UUID.randomUUID().toString().replace("-", "");
    this.memberId = memberId;
    this.ticketId = ticketId;
  }
}
