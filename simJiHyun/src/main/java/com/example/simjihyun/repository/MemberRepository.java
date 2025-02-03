package com.example.simjihyun.repository;

import com.example.simjihyun.entity.SpringMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<SpringMember, Long> {
  boolean existsByMemberIdAndMemberPass(String memberId, String memberPass);
}
