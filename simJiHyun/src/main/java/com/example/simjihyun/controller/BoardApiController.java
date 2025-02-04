package com.example.simjihyun.controller;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/api")
public class BoardApiController {

  @Autowired
  private BoardService boardService;

  //  게시판 목록 가져오기 API
  @RequestMapping(value = "/board", method = RequestMethod.GET)
  public Object home() throws Exception {
    List<SpringBoard> boardList = boardService.query();
    return boardList;
  }

//  상세 정보 가져오기 api
  @GetMapping("/board/{boardIdx}")
  public Object selectBoard(@PathVariable("boardIdx") long boardIdx) {
    SpringBoard board = boardService.selectBoardDetail(boardIdx);
    return board;
  }

  //  게시판 글 등록 API
  @PostMapping("/board/write")
  public void insertBoard(@RequestBody SpringBoard board) {
    boardService.saveBoard(board);
  }

  //  게시판 글 수정 API
  @PutMapping("/board/{boardIdx}")
  public void updateBoard(@PathVariable("boardIdx") long boardIdx, @RequestBody SpringBoard board) {
    board.setBoardIdx(boardIdx);
    boardService.saveBoard(board);
  }

  //  게시판 글 삭제 API
  @DeleteMapping("/board/{boardIdx}")
  public void deleteBoard(@PathVariable("boardIdx") long boardIdx) {
    boardService.deleteBoard(boardIdx);
  }
}
