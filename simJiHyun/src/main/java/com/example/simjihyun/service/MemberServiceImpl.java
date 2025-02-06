package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringMember;
import com.example.simjihyun.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  @Autowired
  private MemberRepository memberRepository;

  //  멤버인지 아닌지 확인
  @Override
  public boolean isMember(String memberId, String memberPass) throws Exception {

    //  memberRepository 에서 제공하는 isUserInfo() 메소드를 사용해 사용자 존재 여부를 확인
    boolean result = memberRepository.existsByMemberIdAndMemberPass(memberId, memberPass);

    if (result) {
//      사용자가 있을 경우 true
      return true;
    } else {
//      사용자가 없을 경우 false
      return false;
    }
  }

  //  회원가입
  @Transactional
  @Override
  public void signUp(SpringMember member) {
    memberRepository.save(member);
  }

//  @Override
//  public int isUniqueId(String memberId) {
//    int count = 0;
//    count = memberRepository.countByMemberId(memberId);
//    return count;
//  }

  //  아이디 있는지 확인
  @Transactional
  @Override
  public boolean existsByMemberId(String memberId) {
    return memberRepository.existsByMemberId(memberId);
  }

  //  마이페이지 자기 정보 확인
  @Override
  public SpringMember selectMemberDetail(String memberId) {
    Optional<SpringMember> optional = memberRepository.findByMemberId(memberId);

    if (optional.isPresent()) {
      SpringMember member = optional.get();
      return member;
    } else {
      throw new NullPointerException();
    }
  }

  //  아이디 및 비밀번호 수정
  public void updateMember(String originalMemberId,
                           String newMemberId,
                           String memberPass,
                           String memberName,
                           String memberEmail) {
//    아이디가 있으면 오류 발생
    if (!originalMemberId.equals(newMemberId) && memberRepository.countByMemberId(newMemberId) > 0) {
      throw new RuntimeException("이미 존재하는 아이디입니다.");
    }
//    아이디가 없으면 쿼리문 실행
    memberRepository.queryUpdate(originalMemberId, newMemberId, memberPass, memberName, memberEmail);
  }

  //  삭제
  @Override
  public void deleteMember(String memberId) {
    memberRepository.deleteByMemberId(memberId);
  }
}
