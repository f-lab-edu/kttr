package com.crs.kttr.member.persistence;

import com.crs.kttr.member.domain.TrainMember;

public interface TrainMemberRepository {
  void save(TrainMember member);

  TrainMember findMember(Long id);
}
