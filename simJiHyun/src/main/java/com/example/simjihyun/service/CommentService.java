package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringComment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

  //  쓰기
  List<SpringComment> save(long boardIdx, SpringComment comment);

  //  댓글 보기
  List<SpringComment> seeComment(long boardIdx);

  //  수정
  void updateComment(String commentWord, String memberId, long commentIdx);

  //  삭제
  void deleteComment(Long commentIdx);
}
