package com.example.simjihyun.repository;

import com.example.simjihyun.dto.BoardListAllDTO;
import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.entity.SpringMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<SpringBoard, Long> {
  //  게시물 리스트
  @Query("select s from SpringBoard as s")
  List<SpringBoard> querySelectAll();

  //  게시물 페이지
  Page<SpringBoard> findAll(Pageable pageable);
}
