package com.example.simjihyun.configuration;

import com.example.simjihyun.Interceptor.LoginCheck;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
//    로그인을 체크해서 로그인 되어 있지 않으면 들어갈 수 없는 페이지 설정
    registry.addInterceptor(new LoginCheck())
            .addPathPatterns("/board/*")
            .addPathPatterns("/comment/*")
            .addPathPatterns("/file/*")
            .addPathPatterns("/member/myPage/*");
  }
}
