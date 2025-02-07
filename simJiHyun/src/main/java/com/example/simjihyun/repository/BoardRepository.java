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
  //  게시물을 전부 다 찾아서 페이징 기능을 넣음
  Page<SpringBoard> findAll(Pageable pageable);
}
