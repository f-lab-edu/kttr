package com.crs.kttr.reservation.exception;

import com.crs.kttr.global.BusinessLogicException;
import com.crs.kttr.global.ServerExceptionDefinedReason;
import lombok.Getter;

@Getter
public class AlreadyHasReservation extends BusinessLogicException {
  public AlreadyHasReservation(ServerExceptionDefinedReason reason) {
    super(reason.getCode(), reason.getMsg(), reason.getInternalMsg(), reason.getStatus());
  }
}
