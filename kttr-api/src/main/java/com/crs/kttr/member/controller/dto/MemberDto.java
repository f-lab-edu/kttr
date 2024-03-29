package com.crs.kttr.member.controller.dto;

import com.crs.kttr.member.model.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {
  private Long id;

  private String signInId;

  public MemberDto(final Member member) {
    this.id = member.getId();
    this.signInId = member.getAccount().getSignInId();

  }
}
