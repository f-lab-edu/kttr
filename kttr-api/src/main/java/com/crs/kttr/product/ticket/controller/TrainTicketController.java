package com.crs.kttr.product.ticket.controller;

import com.crs.kttr.global.dto.Response;
import com.crs.kttr.product.ticket.application.TrainTicketService;
import com.crs.kttr.product.ticket.controller.dto.TrainTicketRegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/ticket")
@RequiredArgsConstructor
public class TrainTicketController {

  final TrainTicketService service;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<Boolean> register(final @Valid @RequestBody TrainTicketRegisterRequest req) {

    service.register(req);

    return Response.ok(Boolean.TRUE);
  }
}
