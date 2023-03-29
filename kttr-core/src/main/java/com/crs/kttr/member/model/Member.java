package com.crs.kttr.member.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "id")
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "membership_code", length = 8)
  private String code;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "signInId", column = @Column(name = "sign_in_id", length = 30)),
    @AttributeOverride(name = "signInPassword", column = @Column(name = "sign_in_password", length = 150))
  })
  private Account account;

  @Column(name = "email", length = 30)
  private String email;

  @Column(name = "phone_number", length = 15)
  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  @Column(name = "member_status", length = 10)
  private MemberStatus status;

  public Member(String signInId, String signInPassword, String email, String phoneNumber) {
    // TODO : generate membershipCode
    this.code = "";
    this.account = new Account(signInId, signInPassword);
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.status = MemberStatus.ACTIVE;
  }
}
