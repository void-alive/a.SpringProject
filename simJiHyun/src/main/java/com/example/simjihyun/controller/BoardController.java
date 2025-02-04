package com.example.simjihyun.controller;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

  @Autowired
  private BoardService boardService;

//  리스트
  @RequestMapping("/home")
  public String home(Model model) throws Exception {
    List<SpringBoard> boardList = boardService.query();
    model.addAttribute("boardList", boardList);
    return "/board/home";
  }

//  상세
  @GetMapping("/detail/{boardIdx}")
  public ModelAndView detail(@PathVariable("boardIdx") Long boardIdx) throws Exception {
    ModelAndView model = new ModelAndView("/board/detail");
    SpringBoard board = boardService.selectBoardDetail(boardIdx);
    model.addObject("board", board);
    return model;
  }

//  수정
  @PutMapping("/detail/{boardIdx}")
  public String put(@PathVariable("boardIdx") Long boardIdx, SpringBoard board) throws Exception {
    boardService.saveBoard(board);
    return "redirect:/board/home";
  }

//  삭제
  @DeleteMapping("/detail/{boardIdx}")
  public String delete(@PathVariable("boardIdx") Long boardIdx) throws Exception {
    boardService.deleteBoard(boardIdx);
    return "redirect:/board/home";
  }

//  쓰기
  @RequestMapping("/write")
  public String write(Model model) throws Exception {
    return "/board/write";
  }

  @PostMapping("/write")
  public String write(SpringBoard board) throws Exception {
    boardService.saveBoard(board);
    return "redirect:/board/home";
  }
}
