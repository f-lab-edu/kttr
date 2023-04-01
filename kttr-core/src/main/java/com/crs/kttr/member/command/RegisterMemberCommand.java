package com.crs.kttr.member.command;

import com.crs.kttr.global.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterMemberCommand extends SelfValidating<RegisterMemberCommand> {
  @NotNull
  @Email
  private String signInId;

  // TODO : password 암호화 처리
  @NotNull
  private String signInPassword;

  @Email
  private String email;

  @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
  private String phoneNumber;

  public RegisterMemberCommand(String signInId, String signInPassword, String email, String phoneNumber) {
    this.signInId = signInId;
    this.signInPassword = signInPassword;
    this.email = email;
    this.phoneNumber = phoneNumber;

    this.validateSelf();
  }
}
