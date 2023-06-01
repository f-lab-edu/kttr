package com.crs.kttr.global;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ServerExceptionDefinedReason {

  UNKNOWN_SERVER_ERROR(1000, "서버 오류 입니다.", "서버 오류 입니다", HttpStatus.INTERNAL_SERVER_ERROR),
  NOT_FOUND_RESOURCE(1004, "자료를 찾을 수 없습니다.", "자료를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

  DUPLICATED_SIGNINID(1100, "이미 사용중인 아이디 입니다.", "이미 사용중인 아이디 입니다.", HttpStatus.BAD_REQUEST),

  ALREADY_RESERVATION(1200, "이미 예약된 내역이 있습니다.", "이미 예약된 내역이 있습니다.", HttpStatus.BAD_REQUEST),

  TRAIN_TICKET_OUT_OF_STOCK(1500, "재고가 부족합니다.", "재고가 부족합니다.", HttpStatus.BAD_REQUEST)

  ;


  ServerExceptionDefinedReason(int code, String internalMsg, String msg, HttpStatus status) {
    this.code = code;
    this.internalMsg = internalMsg;
    this.msg = msg;
    this.status = status;
  }

  private int code;
  private String internalMsg;
  private String msg;
  private HttpStatus status;
}
