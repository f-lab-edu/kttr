package com.crs.kttr.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@ToString
public class Response<T> {
  private ResponseMeta meta;

  private T data;

  public static <T> ResponseEntity<Response<T>> ok(final HttpStatus status,
                                                   final ResponseMeta meta,
                                                   final T data) {
    return ResponseEntity.status(status).body(new Response<>(meta, data));
  }

  public static <T> Response<T> of(final ResponseMeta meta,
                                   final T data) {
    return new Response<>(meta, data);
  }

  public static <T> Response<T> ok(final T data) {
    return of(ResponseMeta.EMPTY, data);
  }
}
