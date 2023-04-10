package com.crs.kttr.member.persistence;

import com.crs.kttr.member.model.Member;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {
  public MemberRepositoryImpl() {
    super(Member.class);
  }

  @Override
  public Boolean existsBySignInId(String signInId) {
    return Boolean.FALSE;
  }
}
