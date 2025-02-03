package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringMember;
import com.example.simjihyun.repository.MemberRepository;
import jakarta.persistence.LockModeType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonWriter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
