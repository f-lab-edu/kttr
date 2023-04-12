package com.crs.kttr.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SeverExceptionDefinedReason {

  UNKNOWN_SERVER_ERROR(1000, "서버 오류 입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
  NOT_FOUND_RESOURCE(1004, "자료를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

  DUPLICATED_SIGNINID(1100, "이미 사용중인 아이디 입니다.", HttpStatus.BAD_REQUEST)
  ;

  SeverExceptionDefinedReason(int code, String msg, HttpStatus status) {
    this.code = code;
    this.msg = msg;
    this.status = status;
  }

  private int code;
  private String msg;
  private HttpStatus status;
}
