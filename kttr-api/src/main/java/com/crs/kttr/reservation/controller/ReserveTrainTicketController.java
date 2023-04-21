package com.crs.kttr.reservation.controller;

import com.crs.kttr.global.dto.Response;
import com.crs.kttr.reservation.application.TicketReservationService;
import com.crs.kttr.reservation.controller.dto.TrainTicketReserveRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/reserve")
@RequiredArgsConstructor
public class ReserveTrainTicketController {

  final TicketReservationService service;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<String> reserve(final @Valid @RequestBody TrainTicketReserveRequest req) {
    return Response.ok(service.reserve(req.getMemberId(), req.getTicketId()));
  }
}
