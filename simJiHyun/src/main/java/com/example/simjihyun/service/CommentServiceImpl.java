package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.entity.SpringComment;
import com.example.simjihyun.repository.BoardRepository;
import com.example.simjihyun.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private BoardRepository boardRepository;

  //  쓰기
  @Override
  public List<SpringComment> save(long boardIdx, SpringComment comment) {
//    boardRepository 의 findById 를 boardIdx 를 기준으로 실행함.
//      만약에 Optional 의 값이 null 이면 "해당 게시물 존재 x" 반환
//    오류가 안 났으니까 SpringComment 의 board 값을 지정함
//    commentRepository 의 save 함수를 실행함
//    commentRepository 의 fideBy... 함수 실행
    SpringBoard board = boardRepository.findById(boardIdx).orElseThrow(() -> new IllegalArgumentException("해당 게시물 존재 x "));
    comment.setBoard(board);
    commentRepository.save(comment);
    return commentRepository.findByBoardBoardIdxOrderByCommentDateDesc(boardIdx);
  }

  //  보기
  @Override
  public List<SpringComment> seeComment(long boardIdx) {
    return commentRepository.findByBoardBoardIdxOrderByCommentDateDesc(boardIdx);
  }

  //  수정
  @Override
  public void updateComment(@RequestParam("newCommentWord") String newCommentWord,
                            @RequestParam("commentId") String commentId,
                            @RequestParam("commentIdx") long commentIdx) {
    commentRepository.queryCommentUpdate(newCommentWord, commentId, commentIdx);
  }

  //  삭제
  @Override
  @Transactional
  public void deleteComment(@RequestParam("commentIdx") long commentIdx) {
    commentRepository.deleteByCommentIdx(commentIdx);
  }
}
