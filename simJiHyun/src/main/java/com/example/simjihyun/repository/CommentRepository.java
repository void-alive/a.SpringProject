package com.example.simjihyun.repository;

import com.example.simjihyun.entity.SpringComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<SpringComment, Long> {
  //  삭제
  void deleteByCommentIdx(long commentIdx);

  //  댓글 달 때 게시물 번호도 같이 달림
  List<SpringComment> findByBoardBoardIdxOrderByCommentDateDesc(Long boardIdx);

  //  수정
//  SpringComment 의 commentId 와 commentIdx 를 기준으로 검색해
//  해당 아이디와 댓글 번호에 해당하는 댓글을 newCommentWord 로 수정함
  @Transactional
  @Modifying
  @Query("UPDATE SpringComment sc SET sc.commentWord = :newCommentWord " +
          "WHERE sc.commentId = :commentId AND sc.commentIdx = :commentIdx")
  void queryCommentUpdate(@Param("newCommentWord") String newCommentWord,
                          @Param("commentId") String commentId,
                          @Param("commentIdx") long commentIdx);


}
