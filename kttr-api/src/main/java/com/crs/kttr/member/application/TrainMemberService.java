package com.crs.kttr.member.application;

import com.crs.kttr.member.domain.TrainMember;

public interface TrainMemberService {

  void join(TrainMember trainMember);

  TrainMember findMember(Long memberId);
}
