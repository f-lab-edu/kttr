package com.crs.kttr.member.persistence;

import com.crs.kttr.member.domain.TrainMember;

import java.util.HashMap;
import java.util.Map;

public class TrainMemberRepositoryImpl implements TrainMemberRepository {
  private Map<Long, TrainMember> store = new HashMap<>();

  @Override
  public void save(TrainMember member) {
    store.put(member.getId(), member);
  }

  @Override
  public TrainMember findMember(Long id) {
    return store.get(id);
  }
}
