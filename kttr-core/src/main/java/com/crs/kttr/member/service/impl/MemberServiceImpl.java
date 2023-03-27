package com.crs.kttr.member.service.impl;

import com.crs.kttr.member.command.RegisterMemberCommand;
import com.crs.kttr.member.model.Member;
import com.crs.kttr.member.model.MemberRepository;
import com.crs.kttr.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  final MemberRepository repo;

  @Override
  public Member register(RegisterMemberCommand command) {
    // TODO : signInId 중복 있는 확인 후 Exception 처리

    return repo.save(new Member(
        command.getSignInId(),
        command.getSignInPassword(),
        command.getEmail(),
        command.getPhoneNumber()
      )
    );
  }
}
