package com.crs.kttr.member.service;

import com.crs.kttr.member.command.RegisterMemberCommand;
import com.crs.kttr.member.model.Member;
import com.crs.kttr.member.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberCRUDService {

  private final MemberRepository repo;

  public Optional<Member> findById(Long id) {
    return repo.findById(id);
  }

  public Member register(RegisterMemberCommand command) {
    return repo.save(new Member(
        command.getSignInId(),
        command.getSignInPassword(),
        command.getEmail(),
        command.getPhoneNumber()
      )
    );
  }

  public Boolean exists(String signInId) {
    return repo.existsByAccount_SignInId(signInId);
  }
}
