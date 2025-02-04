package com.example.simjihyun;

import com.example.simjihyun.repository.BoardRepository;
import com.example.simjihyun.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimJiHyunApplicationTests {

  @Autowired
  private BoardService boardService;

  @Test
  void contextLoads() {
  }
}
