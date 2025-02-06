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
  public SpringBoard saveBoard(SpringBoard board) {
    boardRepository.save(board);
    return board;
  }

  //  페이징
  @Override
  public Page<SpringBoard> getList(int page) {
    List<Sort.Order> sorts = new ArrayList<>();
    sorts.add(Sort.Order.desc("boardIdx"));
    Pageable pageable = PageRequest.of(page, 9, Sort.by(sorts));
    return this.boardRepository.findAll(pageable);
  }

//  @Override
//  public PageResponseDTO<SpringBoard> list(PageRequestDTO pageRequestDTO) {
//    String[] types = pageRequestDTO.getTypes();
//    String keyword = pageRequestDTO.getKeyword();
//    Pageable pageable = pageRequestDTO.getPageable("bno");
//
//    Page<SpringBoard> result = boardRepository.searchAll(types, keyword, pageable);
//
//    List<SpringBoard> dtoList = result.getContent().stream()
//            .map(board -> modelMapper.map(board,SpringBoard.class)).
//            collect(Collectors.toList());
//    return PageResponseDTO.<SpringBoard>withAll()
//            .pageRequestDTO(pageRequestDTO)
//            .dtoList(dtoList)
//            .total((int)result.getTotalElements())
//            .build();
//    return null;
//  }

}
