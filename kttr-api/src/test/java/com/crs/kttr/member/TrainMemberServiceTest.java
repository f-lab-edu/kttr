package com.crs.kttr.member;

import com.crs.kttr.member.application.TrainMemberService;
import com.crs.kttr.member.application.TrainMemberServiceImpl;
import com.crs.kttr.member.domain.TrainMember;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrainMemberServiceTest {
  TrainMemberService memberService = new TrainMemberServiceImpl();

  @Test
  void join() {
    // given
    TrainMember trainMember = new TrainMember(1L, "memberA");

    // when
    memberService.join(trainMember);
    final TrainMember findMember = memberService.findMember(trainMember.getId());

    // then
    Assertions.assertThat(trainMember).isEqualTo(findMember);
  }
}
