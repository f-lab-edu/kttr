package com.crs.kttr.member.controller;

import com.crs.kttr.global.dto.Response;
import com.crs.kttr.member.application.MemberRegisterService;
import com.crs.kttr.member.controller.dto.MemberDto;
import com.crs.kttr.member.controller.dto.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/guest/member")
@RequiredArgsConstructor
public class RegisterMemberApiController {

  final MemberRegisterService service;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<MemberDto> register(final @RequestBody MemberRegisterRequest req) {
    return Response.ok(service.register(req.toCommand()));
  }
}
