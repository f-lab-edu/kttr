package com.crs.kttr.member.persistence;

import com.crs.kttr.member.model.Member;
import com.crs.kttr.member.model.QMember;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {
  private final static QMember MEMBER = QMember.member;

  public MemberRepositoryImpl() {
    super(Member.class);
  }

  @Override
  public Boolean existsBySignInId(String signInId) {
    return from(MEMBER)
            .where(MEMBER.account.signInId.eq(signInId))
            .fetchCount() > 0;
  }
}
