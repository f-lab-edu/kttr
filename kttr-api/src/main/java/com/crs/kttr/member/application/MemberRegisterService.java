package com.crs.kttr.member.application;

import com.crs.kttr.member.command.RegisterMemberCommand;
import com.crs.kttr.member.controller.dto.MemberDto;
import com.crs.kttr.member.model.Member;
import com.crs.kttr.member.service.MemberCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberRegisterService {
  private final MemberCRUDService service;

  public MemberDto register(RegisterMemberCommand command) {
    final Member member = service.register(command);

    return new MemberDto(member);
  }
}
