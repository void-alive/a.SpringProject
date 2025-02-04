package com.example.simjihyun.repository;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.entity.SpringMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<SpringBoard, Long> {
  @Query("select s from SpringBoard as s")
  List<SpringBoard> querySelectAll();

  Page<SpringBoard> findAll(Pageable pageable);

}
