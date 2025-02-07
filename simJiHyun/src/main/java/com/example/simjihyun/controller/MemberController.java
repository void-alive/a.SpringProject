package com.example.simjihyun.controller;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.entity.SpringMember;
import com.example.simjihyun.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {

  @Autowired
  private MemberService memberService;

  //  첫 화면
  @RequestMapping("/first")
  public String login() {
    return "/member/first";
  }

  //  로그인 성공여부 확인
  @PostMapping("/loginProcess")
  public String memberProcess(@RequestParam("memberId") String memberId,
                              @RequestParam("memberPass") String memberPass,
                              HttpServletRequest request) throws Exception {

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
      return "redirect:/member/first";
    }
  }

  //  회원가입 사이트
  @RequestMapping("/signUp")
  public String signUp() {
    return "/member/signUp";
  }

  //  회원가입
  @PostMapping("/signUpProcess")
  @ResponseBody
  public Map<String, Object> signUpProcess(@ModelAttribute SpringMember member) throws Exception {
    Map<String, Object> response = new HashMap<>();
    try {
      memberService.signUp(member);
      response.put("success", true);
      response.put("message", "회원가입 완료");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      response.put("success", false);
      response.put("message", "중복 아이디는 가입이 불가합니다.");
    }
    return response;
  }

  //  아이디 중복 확인
  @GetMapping("/isUniqueId")
  @ResponseBody
  public Map<String, Boolean> isUniqueId(@RequestParam("memberId") String memberId) {
    boolean isUnique = !(memberService.existsByMemberId(memberId));
    Map<String, Boolean> response = new HashMap<>();
    response.put("isUnique", isUnique);
    return response;
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

    return "redirect:/member/first";
  }

  //  비밀번호 확인
  @GetMapping("/checkPassword")
  @ResponseBody
  public Map<String, Object> checkPassWord(@RequestParam("checkPassWord") String checkPassWord, HttpServletRequest request) {
    HttpSession session = request.getSession();
    String sessionPass = (String) session.getAttribute("memberPass");
    Map<String, Object> response = new HashMap<>();

    System.out.println("세션 : " + sessionPass);
    System.out.println("비밀번호 : " + checkPassWord);

    if (sessionPass != null && sessionPass.equals(checkPassWord)) {
      response.put("success", true);
      response.put("message", "비밀번호 일치");
    } else {
      response.put("fail", false);
      response.put("message", "비밀번호 불일치");
    }
    return response;
  }

  //  마이페이지
  @RequestMapping("/myPage/{memberId}")
  public ModelAndView myPage(HttpServletRequest request) throws Exception {
    ModelAndView model = new ModelAndView("/member/myPage");

    HttpSession session = request.getSession();
    String memberId = (String) session.getAttribute("memberId");
    String memberPass = (String) session.getAttribute("memberPass");

    SpringMember member = memberService.selectMemberDetail(memberId);
    model.addObject("member", member);
    model.addObject("memberPass", memberPass);
    return model;
  }

  //  수정
  @Transactional
  @PutMapping("/myPage/{memberId}")
  public String put(HttpServletRequest request,
                    @RequestParam("memberId") String newMemberId,
                    @RequestParam("memberPass") String memberPass,
                    @RequestParam("memberName") String memberName,
                    @RequestParam("memberEmail") String memberEmail) throws Exception {

    HttpSession session = request.getSession();
    String originalMemberId = (String) session.getAttribute("memberId");

    memberService.updateMember(originalMemberId, newMemberId, memberPass, memberName, memberEmail);
    return "redirect:/member/first";
  }

  //  삭제
  @Transactional
  @DeleteMapping("/myPage/{memberId}")
  public String delete(@PathVariable("memberId") String memberId) throws Exception {
    System.out.println("삭제할 회원 ID: " + memberId);
    memberService.deleteMember(memberId);
    return "redirect:/member/first";
  }
}