package com.crs.kttr.product.ticket.exception;

import com.crs.kttr.global.BusinessLogicException;
import com.crs.kttr.global.ServerExceptionDefinedReason;
import lombok.Getter;

@Getter
public class TrainTicketNotFoundException extends BusinessLogicException {
  public TrainTicketNotFoundException(final ServerExceptionDefinedReason reason) {
    super(reason.getCode(), reason.getMsg(), reason.getInternalMsg(), reason.getStatus());
  }
}
