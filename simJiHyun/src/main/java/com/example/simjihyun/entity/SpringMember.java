package com.example.simjihyun.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "spring_member")
@Getter
@NoArgsConstructor
public class SpringMember {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberIdx;

  @Column(nullable = false, unique = true, name = "member_id")
  private String memberId;

  @Column(nullable = false, name = "member_pass")
  private String memberPass;

  @Column(nullable = false, name = "member_name")
  private String memberName;

  @Column(nullable = false, name = "member_email", unique = true)
  private String memberEmail;
}
