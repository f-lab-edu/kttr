package com.crs.kttr.member.domain;

public class TrainMember {
  private Long id;

  private String name;

  public TrainMember(Long id, String name) {
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
