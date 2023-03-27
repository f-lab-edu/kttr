package com.crs.kttr.member.application;

import com.crs.kttr.member.command.RegisterMemberCommand;
import com.crs.kttr.member.interfaces.dto.MemberDto;
import com.crs.kttr.member.model.Member;
import com.crs.kttr.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberRegisterService {

  final MemberService service;

  public MemberDto register(RegisterMemberCommand command) {
    final Member member = service.register(command);

    return new MemberDto(member);
  }
}
