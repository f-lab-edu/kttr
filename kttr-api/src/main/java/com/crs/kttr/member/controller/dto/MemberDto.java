package com.crs.kttr.member.controller.dto;

import com.crs.kttr.member.model.Member;
import lombok.Getter;

@Getter
public class MemberDto {
  private final Long id;

  private final String signInId;

  public MemberDto(final Member member) {
    this.id = member.getId();
    this.signInId = member.getAccount().getSignInId();
  }
}
