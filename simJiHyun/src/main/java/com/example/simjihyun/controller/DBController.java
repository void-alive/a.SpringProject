package com.example.simjihyun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DBController {

  @RequestMapping("/")
  public String index() {
    return "index";
  }
}
