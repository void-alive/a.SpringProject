package com.example.simjihyun.controller;

import com.example.simjihyun.entity.SpringComment;
import com.example.simjihyun.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Log4j2
public class CommentController {

  @Autowired
  private CommentService commentService;

  @ResponseBody
  @PostMapping("/ajax")
  public Object comment(@RequestParam("commentWord") String commentWord) throws Exception{


  }
}
