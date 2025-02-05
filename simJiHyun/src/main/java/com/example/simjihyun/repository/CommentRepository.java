package com.example.simjihyun.repository;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.entity.SpringComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<SpringComment, Long> {
//  삭제
  void deleteByCommentIdx(Long commentIdx);

//  댓글 달 때 게시물 번호도 같이 달림
  List<SpringComment> findByBoardBoardIdxOrderByCommentDateDesc(Long boardIdx);

//  수정
@Modifying
@Query("update SpringComment as sc set sc.commentWord = :commentWord " +
        "where sc.commentId = :MemberId and sc.commentIdx =:commentIdx")
void queryCommentUpdate(@Param("commentWord") String commentWord,
                        @Param("MemberId") String MemberId,
                        @Param("commentIdx") long commentIdx);

}
