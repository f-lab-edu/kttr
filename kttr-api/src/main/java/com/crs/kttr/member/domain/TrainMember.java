package com.crs.kttr.member.domain;

import io.micrometer.common.util.StringUtils;

public class TrainMember {
  private Long id;

  private String name;

  public TrainMember(Long id, String name) {
    if (id == null || id < 1L) {
      throw new IllegalArgumentException();
    }

    if (StringUtils.isEmpty(name)) {
      throw new IllegalArgumentException();
    }

    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
