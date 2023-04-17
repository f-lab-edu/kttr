package com.crs.kttr.member.application;

import com.crs.kttr.global.ServerExceptionDefinedReason;
import com.crs.kttr.member.exception.MemberNotFoundException;
import com.crs.kttr.member.model.Member;
import com.crs.kttr.member.service.MemberCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindMemberService {

  private final MemberCRUDService service;

  public Optional<Member> findById(Long id) {
    return service.findById(id);
  }
}
