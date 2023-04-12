package com.crs.kttr.member.service;

import com.crs.kttr.member.exception.MemberRegisterException;
import com.crs.kttr.member.exception.MemberRegisterExceptionDefinedReason;
import com.crs.kttr.member.command.RegisterMemberCommand;
import com.crs.kttr.member.model.Member;
import com.crs.kttr.member.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCRUDService {
  final MemberRepository repo;

  public Member register(RegisterMemberCommand command) {
    final Boolean exists = repo.existsByAccount_SignInId(command.getSignInId());
    if (exists) {
      throw new MemberRegisterException(MemberRegisterExceptionDefinedReason.DUPLICATED_SIGNINID);
    }

    return repo.save(new Member(
        command.getSignInId(),
        command.getSignInPassword(),
        command.getEmail(),
        command.getPhoneNumber()
      )
    );
  }
}
