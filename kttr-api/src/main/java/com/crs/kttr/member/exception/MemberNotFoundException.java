package com.crs.kttr.member.exception;

public class MemberNotFoundException extends RuntimeException {
  public MemberNotFoundException(String message) {
    super(message);
  }
}
