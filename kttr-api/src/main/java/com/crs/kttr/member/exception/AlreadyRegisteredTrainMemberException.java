package com.crs.kttr.member.exception;

public class AlreadyRegisteredTrainMemberException extends RuntimeException {

  public AlreadyRegisteredTrainMemberException(String message) {
    super(message);
  }
}
