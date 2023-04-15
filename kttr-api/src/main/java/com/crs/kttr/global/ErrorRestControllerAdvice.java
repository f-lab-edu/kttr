package com.crs.kttr.global;

import com.crs.kttr.member.exception.MemberRegisterException;
import com.crs.kttr.global.dto.Response;
import com.crs.kttr.global.dto.ResponseMeta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorRestControllerAdvice {

  @ExceptionHandler(MemberRegisterException.class)
  public ResponseEntity<Response<Object>> httpStatusRestException(final MemberRegisterException e) {
    final int errorCode = e.getCode();
    final String message = e.getMsg();

    log.error("code : {}, message : {}", errorCode, message);

    return Response.error(e.getStatus(), new ResponseMeta(errorCode, message), null);
  }
}
