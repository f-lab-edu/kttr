package com.crs.kttr.product.ticket.exception;

import com.crs.kttr.global.ServerExceptionDefinedReason;
import org.springframework.http.HttpStatus;

public class TrainTicketNotFoundException extends RuntimeException {

  private int code;

  private String internalMsg;

  private String msg;

  private HttpStatus status;

  public TrainTicketNotFoundException(final ServerExceptionDefinedReason reason) {
    this.code = reason.getCode();
    this.msg = reason.getMsg();
    this.internalMsg = reason.getInternalMsg();
    this.status = reason.getStatus();
  }
}
