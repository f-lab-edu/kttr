package com.crs.kttr.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * GenericResponse 공통 응답.
 *
 * @author Heeyeon.
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class GenericResponse<T> {
  private ResponseMeta meta;

  private T data;

  public static <T> ResponseEntity<GenericResponse<T>> of(final HttpStatus status,
                                                          final ResponseMeta meta,
                                                          final T data) {
    return ResponseEntity.status(status).body(new GenericResponse<>(meta, data));
  }

  public static <T> ResponseEntity<GenericResponse<T>> ok(final T data) {
    return of(HttpStatus.OK, ResponseMeta.EMPTY, data);
  }
}
