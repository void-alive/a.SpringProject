package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

  @Autowired
  private BoardRepository boardRepository;

  //  상세
  @Override
  public SpringBoard selectBoardDetail(Long boardIdx) {
    Optional<SpringBoard> optional = boardRepository.findById(boardIdx);

/*  optional : null 값이 올 수 있는 값을 감싸는 wrapper 클래스
      값이 null 이더라도 바로 nullPointerException 이 발생하지 않음.
      .isPresent : optional 객체가 값을 가지고 있다면 true, 없으면 false
      .of() : 값이 절대로 Null 이 아님. Null 이면 NullPointerException 발생
      .ofNullable() : 값이 Null 일수도, 아닐 수도 있음
    값을 가지고 있으므로 객체를 가져오고, 조회수를 1 늘림.
    변경된 값을 저장해서 board 객체를 반환함*/
    if (optional.isPresent()) {
      SpringBoard board = optional.get();
      board.setHitCnt(board.getHitCnt() + 1);
      boardRepository.save(board);
      return board;
    } else {
//      없으면 NullPointerException() 발생
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
  public SpringBoard saveBoard(SpringBoard board) {
    boardRepository.save(board);
    return board;
  }

  //  페이징
  @Override
  public Page<SpringBoard> getList(int page) {
    List<Sort.Order> sorts = new ArrayList<>();
    sorts.add(Sort.Order.desc("boardIdx"));
//    boardIdx 를 기준으로 내림차순 처리함. 한 페이지의 최대 페이지 수는 9개로
    Pageable pageable = PageRequest.of(page, 9, Sort.by(sorts));
    return this.boardRepository.findAll(pageable);
  }
}
