package com.crs.kttr.member.persistence;

import com.crs.kttr.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
