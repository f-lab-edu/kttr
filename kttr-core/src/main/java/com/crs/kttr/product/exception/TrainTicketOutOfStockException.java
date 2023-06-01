package com.crs.kttr.product.exception;

import com.crs.kttr.global.BusinessLogicException;
import com.crs.kttr.global.ServerExceptionDefinedReason;
import lombok.Getter;

@Getter
public class TrainTicketOutOfStockException extends BusinessLogicException {
  public TrainTicketOutOfStockException(final ServerExceptionDefinedReason reason) {
    super(reason.getCode(), reason.getMsg(), reason.getInternalMsg(), reason.getStatus());
  }
}
