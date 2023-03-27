package com.crs.kttr.member.interfaces.dto;

import com.crs.kttr.member.model.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {
  private Long id;

  private String signId;

  public MemberDto(final Member member) {
    this.id = member.getId();
    this.signId = member.getAccount().getSignInId();

  }
}
