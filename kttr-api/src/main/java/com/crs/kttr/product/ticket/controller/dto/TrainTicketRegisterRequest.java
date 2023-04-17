package com.crs.kttr.product.ticket.controller.dto;

import lombok.Value;

@Value
public class TrainTicketRegisterRequest {
  final String name;

  final Integer maxQuantity;
}
