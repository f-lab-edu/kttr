package com.crs.kttr.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class BusinessLogicException extends RuntimeException {

  private int code;

  private String internalMsg;

  private String msg;

  private HttpStatus status;
}
