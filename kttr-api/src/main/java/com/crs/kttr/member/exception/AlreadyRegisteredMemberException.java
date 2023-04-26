package com.crs.kttr.member.exception;

import com.crs.kttr.global.BusinessLogicException;
import com.crs.kttr.global.ServerExceptionDefinedReason;

public class AlreadyRegisteredMemberException extends BusinessLogicException {

  public AlreadyRegisteredMemberException(final ServerExceptionDefinedReason reason) {
    super(reason.getCode(), reason.getMsg(), reason.getInternalMsg(), reason.getStatus());
  }

}
