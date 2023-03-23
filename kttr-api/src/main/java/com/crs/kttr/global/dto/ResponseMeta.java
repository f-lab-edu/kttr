package com.crs.kttr.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMeta {
  public static final ResponseMeta EMPTY = new ResponseMeta(0, "");
  private int code;
  private String msg;
}
