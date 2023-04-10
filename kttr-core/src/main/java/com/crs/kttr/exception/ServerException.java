package com.crs.kttr.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServerException extends RuntimeException {
  private int code;

  private String msg;

  private HttpStatus status;

  public ServerException(int code, String msg, HttpStatus status, String message,  Throwable cause) {
    super(message, cause);
    this.code = code;
    this.msg = msg;
    this.status = status;
  }

  public ServerException(SeverExceptionDefinedReason reason) {
    this(reason.getCode(), reason.getMsg(), reason.getStatus(), null, null);
  }
}
