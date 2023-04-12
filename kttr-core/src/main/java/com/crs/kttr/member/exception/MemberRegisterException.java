package com.crs.kttr.member.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberRegisterException extends RuntimeException {
  private int code;

  private String internalMsg;

  private String msg;

  private HttpStatus status;

  public MemberRegisterException(int code, String msg, HttpStatus status, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
    this.msg = msg;
    this.status = status;
  }

  public MemberRegisterException(MemberRegisterExceptionDefinedReason reason) {
    // TODO : Throwable cause null 테스트 필요 -> 오류 확인 후 재작성 필요
    this(reason.getCode(), reason.getMsg(), reason.getStatus(), null, null);
  }
}
