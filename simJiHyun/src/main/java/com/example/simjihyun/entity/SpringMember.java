package com.example.simjihyun.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "spring_member")
@Getter
@Setter
@NoArgsConstructor
public class SpringMember {
  @Id
  @Column(name = "member_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String memberId;

  @Column(nullable = false, name = "member_pass")
  private String memberPass;

  @Column(nullable = false, name = "member_name")
  private String memberName;

  @Column(nullable = false, name = "member_email")
  private String memberEmail;

  @Builder
  public SpringMember(String id, String password, String name, String email) {
    this.memberId = id;
    this.memberPass = password;
    this.memberName = name;
    this.memberEmail = email;
  }
}
