package com.example.simjihyun.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginCheck implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
//    세션을 가져옴
    HttpSession session = req.getSession();

//    세션의 memberId 가 null 이라면 (로그인을 안함) "/member/first" 인 로그인 화면으로 보냄
    if (session.getAttribute("memberId") == null) {
      res.sendRedirect("/member/first");
      return false;
    } else {
//      세션 정보가 있는 경우 최대 유지 시간을 60분으로 정함
      session.setMaxInactiveInterval(60);
      return true;
    }
  }
}
