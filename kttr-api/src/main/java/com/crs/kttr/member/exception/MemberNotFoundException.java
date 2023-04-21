package com.crs.kttr.member.exception;

import com.crs.kttr.global.ServerExceptionDefinedReason;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends RuntimeException {
  private int code;

  private String internalMsg;

  private String msg;

  private HttpStatus status;

  public MemberNotFoundException(final ServerExceptionDefinedReason reason) {
    this.code = reason.getCode();
    this.msg = reason.getMsg();
    this.internalMsg = reason.getInternalMsg();
    this.status = reason.getStatus();
  }
}
