package com.example.simjihyun.controller;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.entity.SpringFile;
import com.example.simjihyun.service.BoardService;
import com.example.simjihyun.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/board")
public class BoardController {

  @Autowired
  private BoardService boardService;

  @Autowired
  private FileService fileService;

  //  페이징 리스트
  @RequestMapping("/home")
  public String home(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
    Page<SpringBoard> paging = this.boardService.getList(page);
    model.addAttribute("boardList", paging);
    return "/board/home";
  }

  //  상세
  @GetMapping("/detail/{boardIdx}")
  public ModelAndView detail(@PathVariable("boardIdx") Long boardIdx) throws Exception {
    ModelAndView model = new ModelAndView("/board/detail");
    SpringBoard board = boardService.selectBoardDetail(boardIdx);
    List<SpringFile> file = fileService.findAllFile(boardIdx);
    model.addObject("board", board);
    model.addObject("file", file);
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

  //  쓰기 화면
  @GetMapping("/write")
  public String write() {
    return "/board/write";
  }

  //  쓰기
  @RequestMapping("/write")
  public String write(@ModelAttribute SpringBoard board,
                      @RequestParam(value = "files", required = false) MultipartFile[] files,
                      HttpServletRequest request) throws Exception {
    HttpSession session = request.getSession();
    String memberId = (String) session.getAttribute("memberId");
    board.setBoardWriter(memberId);

    SpringBoard saveBoard = boardService.saveBoard(board);

    if (files != null && files.length > 0) {
      List<SpringFile> savedFiles = fileService.saveFiles(files);
      for (SpringFile savedFile : savedFiles) {
        savedFile.setBoard(saveBoard);
        fileService.saveFileEntity(savedFile);
      }
    }
    return "redirect:/board/home";
  }
}
