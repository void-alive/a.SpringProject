package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringBoard;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {
  //  상세
  SpringBoard selectBoardDetail(Long boardIdx);

  //  삭제
  void deleteBoard(Long boardIdx);

  //  글쓰기
  SpringBoard saveBoard(SpringBoard board);

  //  페이징
  Page<SpringBoard> getList(int page);
}
