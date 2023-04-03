package com.crs.kttr.member.controller.dto;

import com.crs.kttr.member.command.RegisterMemberCommand;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegisterRequest {
  @NotNull
  private String signInId;

  @NotNull
  private String signInPassword;

  private String email;

  private String phoneNumber;

  public RegisterMemberCommand toCommand() {
    return new RegisterMemberCommand(this.signInId, this.signInPassword, this.email, this.phoneNumber);
  }
}
