package com.crs.kttr.member.application;

import com.crs.kttr.member.domain.TrainMember;
import com.crs.kttr.member.persistence.TrainMemberRepository;
import com.crs.kttr.member.persistence.TrainMemberRepositoryImpl;

public class TrainMemberServiceImpl implements TrainMemberService {
  private final TrainMemberRepository repo = new TrainMemberRepositoryImpl();

  @Override
  public void join(TrainMember trainMember) {
    repo.save(trainMember);
  }

  @Override
  public TrainMember findMember(Long memberId) {
    return repo.findMember(memberId);
  }
}
