package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

  @Autowired
  private BoardRepository boardRepository;

  //  리스트
  @Override
  public List<SpringBoard> query() throws Exception {
    List<SpringBoard> query1 = boardRepository.querySelectAll();

    if (query1 != null) {
      for (SpringBoard board : query1) {
        System.out.println(board.getBoardIdx());
        System.out.println(board.getBoardTitle());
        System.out.println(board.getBoardContent());
        System.out.println(board.getBoardWriter());
        System.out.println(board.getBoardDate());
        System.out.println(board.getHitCnt());
      }
    } else {
      System.out.println("null");
    }
    return query1;
  }

  //  상세
  @Override
  public SpringBoard selectBoardDetail(Long boardIdx) {
    Optional<SpringBoard> optional = boardRepository.findById(boardIdx);

    if (optional.isPresent()) {
      SpringBoard board = optional.get();
      board.setHitCnt(board.getHitCnt() + 1);
      boardRepository.save(board);
      return board;
    } else {
      throw new NullPointerException();
    }
  }

//  삭제
  @Override
  public void deleteBoard(Long boardIdx) {
    boardRepository.deleteById(boardIdx);
  }

//  글쓰기 및 수정
  @Override
  public void saveBoard(SpringBoard board) {
    boardRepository.save(board);
  }
}
