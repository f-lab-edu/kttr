package com.crs.kttr.member;

import com.crs.kttr.member.command.RegisterMemberCommand;
import com.crs.kttr.member.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

public class RegisterMemberTest {

  @MockBean
  MemberService memberService;

  @Test
  @DisplayName(value = "로그인 아이디가 중복이 있는 경우")
  void duplicated_signInId() {
  }

  @Test
  @DisplayName(value = "로그인 아이디가 null인 경우")
  void isnull_signInId()  {
    Assertions.assertThrows(jakarta.validation.ConstraintViolationException.class, () -> {
      RegisterMemberCommand.builder()
        .signInId(null)
        .signInPassword("password")
        .build();
    });
  }

  @Test
  @DisplayName(value = "로그인 아이디 형식이 맞지 않은 경우")
  void invalid_format_signInId_command()  {
    Assertions.assertThrows(jakarta.validation.ConstraintViolationException.class, () -> {
      RegisterMemberCommand.builder()
        .signInId("test")
        .signInPassword("password")
        .build();
    });
  }

  @Test
  @DisplayName(value = "로그인 패스워드가 null인 경우")
  void isnull_signInPassword_command()  {
    Assertions.assertThrows(jakarta.validation.ConstraintViolationException.class, () -> {
      RegisterMemberCommand.builder()
        .signInId("test@test.com")
        .signInPassword(null)
        .build();
    });
  }

  @Test
  @DisplayName(value = "이메일이 null인 경우")
  void isnull_email() {
    RegisterMemberCommand.builder()
      .signInId("test@test.com")
      .signInPassword("password")
      .email(null)
      .build();
  }


  @Test
  @DisplayName(value = "이메일 형식이 맞지 않은 경우")
  void invalid_format_email() {
    Assertions.assertThrows(jakarta.validation.ConstraintViolationException.class, () -> {
      RegisterMemberCommand.builder()
        .signInId("test@test.com")
        .signInPassword("password")
        .email("test")
        .build();
    });
  }

  @Test
  @DisplayName(value = "휴대폰 번호가 null인 경우 ")
  void isnull_phone_number() {
    RegisterMemberCommand.builder()
      .signInId("test@test.com")
      .signInPassword("password")
      .email("test@test.com")
      .phoneNumber(null)
      .build();
  }

  @Test
  @DisplayName(value = "휴대폰 형식이 맞지 않은 경우")
  void invalid_format_phone_number() {
    Assertions.assertThrows(jakarta.validation.ConstraintViolationException.class, () -> {
      RegisterMemberCommand.builder()
        .signInId("test@test.com")
        .signInPassword("password")
        .email("test@test.com")
        .phoneNumber("010")
        .build();
    });
  }

}
