package com.crs.kttr.product.ticket.exception;

public class AlreadyRegisteredTrainTicketException extends RuntimeException {

  public AlreadyRegisteredTrainTicketException(String message) {
    super(message);
  }
}
