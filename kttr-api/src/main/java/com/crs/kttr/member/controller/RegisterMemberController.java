package com.crs.kttr.member.controller;

import com.crs.kttr.global.dto.Response;
import com.crs.kttr.member.application.MemberRegisterService;
import com.crs.kttr.member.controller.dto.MemberDto;
import com.crs.kttr.member.controller.dto.MemberRegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/guest/member")
@RequiredArgsConstructor
public class RegisterMemberController {

  final MemberRegisterService service;

  // TODO : Valid 오류 응답 구현 필요
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<MemberDto> register(final @Valid @RequestBody MemberRegisterRequest req) {
    return Response.ok(service.register(req.toCommand()));
  }
}
