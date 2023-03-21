package com.crs.kttr.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 응답 시 서버에서 정의한 meta info (code, msg).
 *
 * @author Heeyeon.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMeta {
  public static final ResponseMeta EMPTY = new ResponseMeta(0, "");
  private int code;
  private String msg;
}
