package com.example.simjihyun.repository;

import com.example.simjihyun.entity.SpringMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<SpringMember, Long> {
  boolean existsByMemberIdAndMemberPass(String memberId, String memberPass);

  Optional<SpringMember> findByMemberId(String memberId);

  void deleteByMemberId(String memberId);

//  아이디가 있는지
  @Query("select count(sm) from SpringMember sm where sm.memberId = :newMemberId")
  int countByMemberId(@Param("newMemberId") String newMemberId);

//  업데이트
  @Modifying
  @Query("update SpringMember sm set sm.memberId = :newMemberId, sm.memberPass = :memberPass, " +
          "sm.memberName = :memberName, sm.memberEmail = :memberEmail " +
          "where sm.memberId = :originalMemberId")
  void queryUpdate(@Param("originalMemberId") String originalMemberId,
                   @Param("newMemberId") String newMemberId,
                   @Param("memberPass") String memberPass,
                   @Param("memberName") String memberName,
                   @Param("memberEmail") String memberEmail);

  Page<SpringMember> findAll(Pageable pageable);

  boolean existsByMemberId(String memberId);
}
