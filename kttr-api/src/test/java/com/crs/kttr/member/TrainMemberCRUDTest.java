package com.crs.kttr.member;

import com.crs.kttr.member.application.TrainMemberService;
import com.crs.kttr.member.application.TrainMemberServiceImpl;
import com.crs.kttr.member.domain.TrainMember;
import com.crs.kttr.member.exception.AlreadyRegisteredTrainMemberException;
import com.crs.kttr.member.exception.MemberNotFoundException;
import com.crs.kttr.product.ticket.exception.TrainTicketNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TrainMemberCRUDTest {
  TrainMemberService memberService;

  @BeforeEach
  void setUp() {
    memberService = new TrainMemberServiceImpl();
  }

  @Test
  @DisplayName("id가 null일때")
  void isNullId() {
    Assertions.assertThrows(IllegalArgumentException.class,
      () -> new TrainMember(null, "memberA"));
  }

  @Test
  @DisplayName("id가 0일때")
  void isZeroId() {
    Assertions.assertThrows(IllegalArgumentException.class,
      () -> new TrainMember(0L, "memberA"));
  }

  @Test
  @DisplayName("id가 음수일때")
  void isNegativeId() {
    Assertions.assertThrows(IllegalArgumentException.class,
      () -> new TrainMember(-1L, "memberA"));
  }
  @Test
  @DisplayName("이름이 null 일때")
  void isNullName() {
    Assertions.assertThrows(IllegalArgumentException.class,
      () -> new TrainMember(1L, null));
  }

  @Test
  @DisplayName("이름이 공백 일때")
  void isEmptyName() {
    Assertions.assertThrows(IllegalArgumentException.class,
      () -> new TrainMember(1L, ""));
  }

  @Test
  @DisplayName("회원 가입")
  void join() {
    // given
    TrainMember trainMember = new TrainMember(1L, "memberA");

    // when
    memberService.join(trainMember);

    // then
    Assertions.assertEquals(trainMember.getId(), 1L);
  }


  @Test
  @DisplayName("중복 회원 가입 시 오류 처리")
  void duplicatedJoin() {
    TrainMember trainMemberA = new TrainMember(2L, "memberA");
    TrainMember trainMemberB = new TrainMember(2L, "memberA");

    memberService.join(trainMemberA);

    Assertions.assertThrows(AlreadyRegisteredTrainMemberException.class,
      () -> memberService.join(trainMemberB)
    );
  }

  @Test
  @DisplayName("회원 조회")
  void findMember() {
    TrainMember trainMemberA = new TrainMember(1L, "memberA");

    memberService.join(trainMemberA);

    final TrainMember member = memberService.findMember(1L);

    Assertions.assertEquals(member.getId(), 1L);
  }

  @Test
  @DisplayName("회원 조회 시 없는 경우")
  void notFoundTrainMember() {
    Assertions.assertThrows(MemberNotFoundException.class, () -> {
      memberService.findMember(0L);
    });
  }
}
