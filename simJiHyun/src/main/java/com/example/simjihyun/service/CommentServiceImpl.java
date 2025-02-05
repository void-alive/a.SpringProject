package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.entity.SpringComment;
import com.example.simjihyun.repository.BoardRepository;
import com.example.simjihyun.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private BoardRepository boardRepository;

  //  쓰기
@Override
public List<SpringComment> save(long boardIdx, SpringComment comment) {
    SpringBoard board = boardRepository.findById(boardIdx).orElseThrow(()-> new IllegalArgumentException("해당 게시물 존재 x "));
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
public void updateComment(String commentWord, String memberId, long commentIdx){
    commentRepository.queryCommentUpdate(memberId, commentWord, commentIdx);
  }

//  삭제
@Override
public void deleteComment(Long commentIdx){
    commentRepository.deleteByCommentIdx(commentIdx);
  }
}
