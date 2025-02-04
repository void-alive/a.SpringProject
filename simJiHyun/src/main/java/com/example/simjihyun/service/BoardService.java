package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringBoard;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {

  List<SpringBoard> query() throws Exception;

  //  상세
  SpringBoard selectBoardDetail(Long boardIdx);

  void deleteBoard(Long boardIdx);

  void saveBoard(SpringBoard board);

  //  페이징
  Page<SpringBoard> getList(int page);
}
