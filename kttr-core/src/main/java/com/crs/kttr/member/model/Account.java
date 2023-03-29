package com.crs.kttr.member.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class Account {

  @Column(name = "sign_in_id", length = 30)
  private String signInId;

  @Column(name = "sign_in_password", length = 150)
  private String signInPassword;
}
