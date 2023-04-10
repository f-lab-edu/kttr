package com.crs.kttr.member.persistence;

public interface MemberRepositoryCustom {
  Boolean existsBySignInId(String signInId);
}
