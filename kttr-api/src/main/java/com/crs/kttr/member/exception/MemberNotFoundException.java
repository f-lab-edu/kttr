package com.crs.kttr.member.exception;

import com.crs.kttr.global.BusinessLogicException;
import com.crs.kttr.global.ServerExceptionDefinedReason;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberNotFoundException extends BusinessLogicException {

  public MemberNotFoundException(final ServerExceptionDefinedReason reason) {
    super(reason.getCode(), reason.getMsg(), reason.getInternalMsg(), reason.getStatus());
  }

}
