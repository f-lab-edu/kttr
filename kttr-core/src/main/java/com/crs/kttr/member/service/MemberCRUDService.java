package com.crs.kttr.member.service;

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
