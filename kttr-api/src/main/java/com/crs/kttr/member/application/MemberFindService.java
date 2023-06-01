package com.crs.kttr.member.application;

import com.crs.kttr.member.model.Member;
import com.crs.kttr.member.service.MemberCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberFindService {

  private final MemberCRUDService service;

  public Optional<Member> find(Long id) {
    return service.find(id);
  }
}
