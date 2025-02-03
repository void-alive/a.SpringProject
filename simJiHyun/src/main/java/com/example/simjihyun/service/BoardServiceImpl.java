package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

  @Autowired
  private BoardRepository boardRepository;

  @Override
  public List<SpringBoard> query() throws Exception{
    List<SpringBoard> query1 = boardRepository.querySelectAll();

    if(query1 != null){
      for(SpringBoard board : query1){
        System.out.println(board.getBoardIdx());
        System.out.println(board.getBoardTitle());
        System.out.println(board.getBoardContent());
        System.out.println(board.getBoardWriter());
        System.out.println(board.getBoardDate());
        System.out.println(board.getHitCnt());
      }
    }
    else{
      System.out.println("null");
    }
    return query1;
  }
}
