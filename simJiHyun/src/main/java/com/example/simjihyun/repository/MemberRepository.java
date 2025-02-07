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
//  memberId 와 memberPass 를 기준으로 있는 멤버인지 확인
  boolean existsByMemberIdAndMemberPass(String memberId, String memberPass);

//  memberId 를 기준으로 찾기
  Optional<SpringMember> findByMemberId(String memberId);

//  memberId 를 기준으로 지우기
  void deleteByMemberId(String memberId);

//  아이디가 있는지 확인하기
  @Query("select count(sm) from SpringMember sm where sm.memberId = :newMemberId")
  int countByMemberId(@Param("newMemberId") String newMemberId);

//  현재 쓰고 있는 originalMemberId 를 기준으로 찾아서
//  새로운 memberId, memberPass, memberName, memberEmail 설정하기
  @Modifying
  @Query("update SpringMember sm set sm.memberId = :newMemberId, sm.memberPass = :memberPass, " +
          "sm.memberName = :memberName, sm.memberEmail = :memberEmail " +
          "where sm.memberId = :originalMemberId")
  void queryUpdate(@Param("originalMemberId") String originalMemberId,
                   @Param("newMemberId") String newMemberId,
                   @Param("memberPass") String memberPass,
                   @Param("memberName") String memberName,
                   @Param("memberEmail") String memberEmail);

//  페이지로 만들려고 넣은듯?
  Page<SpringMember> findAll(Pageable pageable);

//  존재하는 아이디인지 확인하기
  boolean existsByMemberId(String memberId);
}
