package com.crs.kttr.member.application;

import com.crs.kttr.member.domain.TrainMember;
import com.crs.kttr.member.exception.AlreadyRegisteredTrainMemberException;
import com.crs.kttr.member.exception.MemberNotFoundException;
import com.crs.kttr.member.persistence.TrainMemberRepository;
import com.crs.kttr.member.persistence.TrainMemberRepositoryImpl;

public class TrainMemberServiceImpl implements TrainMemberService {
  private final TrainMemberRepository repo = new TrainMemberRepositoryImpl();

  @Override
  public void join(TrainMember trainMember) {
    if (existsById(trainMember.getId())) {
      throw new AlreadyRegisteredTrainMemberException("이미 등록된 회원입니다.");
    }

    repo.save(trainMember);
  }

  private boolean existsById(Long memberId) {
    return repo.findMember(memberId) != null;
  }

  @Override
  public TrainMember findMember(Long memberId) {
    final TrainMember member = repo.findMember(memberId);
    if (member == null) {
      throw new MemberNotFoundException("등록되지 않은 회원입니다.");
    }

    return member;
  }
}
