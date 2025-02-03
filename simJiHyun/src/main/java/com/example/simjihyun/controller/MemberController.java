package com.example.simjihyun.controller;

import com.example.simjihyun.entity.SpringMember;
import com.example.simjihyun.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class MemberController {

  @Autowired
  private MemberService memberService;

  //  첫 화면
  @RequestMapping("/first")
  public String login(Model model) {
    return "/login/first";
  }

  //  로그인 성공여부 확인
  @PostMapping("/loginProcess")
  public String loginProcess(@RequestParam("memberId") String memberId, @RequestParam("memberPass") String memberPass, HttpServletRequest request) throws Exception {

    //  id 와 비밀번호를 받아서 SpringMemberDB 에 있는지 확인한다
    boolean result = memberService.isMember(memberId, memberPass);

//   있다면 로그인에 성공하고 세션을 설정한다
    if (result) {
      HttpSession session = request.getSession();
      session.setAttribute("memberId", memberId);
      session.setAttribute("memberPass", memberPass);
      System.out.println("로그인 성공");
      return "redirect:/board/home";
    }

//  없다면 "아이디 혹은 비밀번호가 틀렸습니다" 를 출력
//  일단 성공 실패만 가능하게끔 만듦. 시간 나면 알람도 만들것
    else {
      System.out.println("로그인 실패");
      return "redirect:/login/first";
    }
  }

  //  회원가입 사이트
  @RequestMapping("/signUp")
  public String signUp(Model model) {
    return "/login/signUp";
  }

  //  회원가입 절차
//  1. 아이디, 비밀번호, 이름, 이메일을 입력한다
//  1-1. 아이디, 이메일은 중복이 불가능하다
//    만일 중복된 아이디, 이메일이라면 다시 작성하게 한다
//  1-2. 가능하다면 해당 이메일로 메일을 보내는걸 넣고 싶다
//  2. 비밀번호는 확인절차를 들어간다
  @PostMapping("/signUpProcess")
  public String signUpProcess(SpringMember member)
          throws Exception {
    memberService.signUp(member);
    return "redirect:/login/first";
  }

  //  로그아웃
  @RequestMapping("/logout")
  public String logout(HttpServletRequest request) {

//    세션이 있는지 확인
    HttpSession session = request.getSession();
//    있다면 세션의 memberId 와 memberPass 값을 지운다
    session.removeAttribute("memberId");
    session.removeAttribute("memberPass");
//    나머지 세션도 다 지운다
    session.invalidate();

    return "redirect:/login/first";
  }
}