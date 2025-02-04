package com.example.simjihyun.repository;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.entity.SpringComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<SpringComment, Long> {
  //  댓글 등록 : save(comment) 사용
//  댓글 삭제

//  댓글 수정

//  댓글 보기
}
