package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringMember;
import com.example.simjihyun.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

public interface MemberService {
  //  회원여부 판단
  boolean isMember(@RequestParam("memberId") String memberId,
                   @RequestParam("memberPass") String memberPass) throws Exception;

  //  회원가입
  void signUp(SpringMember member);

  //  아이디 있는지 확인
  @Transactional
  boolean existsByMemberId(String memberId);

  //  마이페이지
  SpringMember selectMemberDetail(String memberId);

  //  수정
  void updateMember(@RequestParam("originalMemberId") String originalMemberId,
                    @RequestParam("memberId") String newMemberId,
                    @RequestParam("memberPass") String memberPass,
                    @RequestParam("memberName") String memberName,
                    @RequestParam("memberEmail") String memberEmail);

  //  삭제
  void deleteMember(String memberId);
}
