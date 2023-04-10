package com.crs.kttr.global;

import com.crs.kttr.exception.ServerException;
import com.crs.kttr.global.dto.Response;
import com.crs.kttr.global.dto.ResponseMeta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ErrorControllerAdvice {

  @ExceptionHandler(ServerException.class)
  @ResponseBody
  public ResponseEntity<Response<Object>> httpStatusRestException(final ServerException e) {
    e.printStackTrace();
    final int errorCode = e.getCode();
    final String message = e.getMsg();

    log.warn(message);

    return Response.ok(e.getStatus(), new ResponseMeta(errorCode, message), null);
  }
}
