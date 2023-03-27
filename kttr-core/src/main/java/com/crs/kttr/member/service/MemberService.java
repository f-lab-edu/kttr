package com.crs.kttr.member.service;

import com.crs.kttr.member.model.Member;
import com.crs.kttr.member.command.RegisterMemberCommand;

public interface MemberService {
  Member register(RegisterMemberCommand command);

}
