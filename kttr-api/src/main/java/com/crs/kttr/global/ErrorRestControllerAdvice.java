package com.crs.kttr.global;

import com.crs.kttr.member.exception.MemberRegisterException;
import com.crs.kttr.global.dto.Response;
import com.crs.kttr.global.dto.ResponseMeta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorRestControllerAdvice {

  @ExceptionHandler(MemberRegisterException.class)
  public ResponseEntity<Response<Object>> httpStatusRestException(final MemberRegisterException e) {
    final int errorCode = e.getCode();
    final String message = e.getMsg();

    return Response.ok(e.getStatus(), new ResponseMeta(errorCode, message), null);
  }
}
