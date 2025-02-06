package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringComment;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface CommentService {

  //  쓰기
  List<SpringComment> save(long boardIdx, SpringComment comment);

  //  댓글 보기
  List<SpringComment> seeComment(long boardIdx);

  //  수정
  void updateComment(@RequestParam("newCommentWord") String newCommentWord,
                     @RequestParam("commentId") String commentId,
                     @RequestParam("commentIdx") long commentIdx);

  //  삭제
  void deleteComment(@RequestParam("commentIdx") long commentIdx);
}
