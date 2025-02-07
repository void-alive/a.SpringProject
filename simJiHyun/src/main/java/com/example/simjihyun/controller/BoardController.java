package com.example.simjihyun.controller;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.entity.SpringFile;
import com.example.simjihyun.service.BoardService;
import com.example.simjihyun.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

  //  페이징 리스트. 0페이지에서 시작함
  @RequestMapping("/home")
  public String home(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
    Page<SpringBoard> paging = this.boardService.getList(page);
    model.addAttribute("boardList", paging);
    return "/board/home";
  }

  //  상세
  @GetMapping("/detail/{boardIdx}")
  public ModelAndView detail(@PathVariable("boardIdx") Long boardIdx) throws Exception {
//    화면은 /board/detail
    ModelAndView model = new ModelAndView("/board/detail");
//    boardService 의 selectBoardDetail 을 실행함. 
//    파일은 여러 개 들어갈 수도 있어서 List<> 사용. 
//    한 개(혹은 여러개) 의 파일을 boardIdx 를 기준으로 전부 가져옴
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
//    세션에서 memberId 값을 받아서 memberId 변수에 넣음
//    SpringBoard 를 board 라고 부르기로 함.
//    board 의 setBoardWriter 를 통해서 boardWriter 를 memberId 로 지정함
    HttpSession session = request.getSession();
    String memberId = (String) session.getAttribute("memberId");
    board.setBoardWriter(memberId);

//    SpringBoard 를 saveBoard 라고 부르기로 함. boardService 의 saveBoard 함수 실행
    SpringBoard saveBoard = boardService.saveBoard(board);

//    만약에 파일이 null 이 아니고 길이가 0보다 크다면 (최소 한 개 이상 있다면)
//    SpringFile 의 List 를 savedFiles 라고 부르기로 함. 
//      savedFiles 에 fileService 의 saveFiles 를 실행해서 값을 넣음
//    SpringFile 을 saveFile 이라고 부르기로 함.
//      savedFile 이라는 변수가 savedFiles 의 크기까지 
//      SpringFile 과 연결된 SpringBoard 에 값을 저장함
//      그리고 fileService 의 saveFileEntity 를 실행함
//    다 하고 나면 /board/home 으로 이동
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
