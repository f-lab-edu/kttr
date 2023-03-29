package com.crs.kttr.member.command;

import com.crs.kttr.global.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class RegisterMemberCommand extends SelfValidating<RegisterMemberCommand> {
  @NotNull
  private String signInId;

  // TODO : password 암호화 처리
  @NotNull
  private String signInPassword;

  private String email;

  private String phoneNumber;

  public RegisterMemberCommand(String signInId, String signInPassword, String email, String phoneNumber) {
    this.signInId = signInId;
    this.signInPassword = signInPassword;
    this.email = email;
    this.phoneNumber = phoneNumber;

    this.validateSelf();
  }
}
