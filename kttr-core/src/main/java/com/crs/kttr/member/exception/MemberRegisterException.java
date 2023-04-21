package com.crs.kttr.member.exception;

import com.crs.kttr.global.ServerExceptionDefinedReason;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberRegisterException extends RuntimeException {
  private int code;

  private String internalMsg;

  private String msg;

  private HttpStatus status;

  public MemberRegisterException(ServerExceptionDefinedReason reason) {
    this.code = reason.getCode();
    this.msg = reason.getMsg();
    this.internalMsg = reason.getInternalMsg();
    this.status = reason.getStatus();
  }
}
