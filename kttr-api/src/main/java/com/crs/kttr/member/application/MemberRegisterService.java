package com.crs.kttr.member.application;

import com.crs.kttr.global.ServerExceptionDefinedReason;
import com.crs.kttr.member.command.RegisterMemberCommand;
import com.crs.kttr.member.controller.dto.MemberDto;
import com.crs.kttr.member.exception.AlreadyRegisteredMemberException;
import com.crs.kttr.member.model.Member;
import com.crs.kttr.member.service.MemberCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberRegisterService {
  private final MemberCRUDService service;

  public MemberDto register(RegisterMemberCommand command) {
    final Boolean exists = service.exists(command.getSignInId());
    if (exists) {
      throw new AlreadyRegisteredMemberException(ServerExceptionDefinedReason.DUPLICATED_SIGNINID);
    }

    final Member member = service.register(command);

    return new MemberDto(member);
  }
}
