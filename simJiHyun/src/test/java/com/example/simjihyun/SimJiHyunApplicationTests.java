package com.example.simjihyun;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.entity.SpringComment;
import com.example.simjihyun.entity.SpringFile;
import com.example.simjihyun.repository.BoardRepository;
import com.example.simjihyun.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class SimJiHyunApplicationTests {

  @Autowired
  private BoardService boardService;
  @Autowired
  private BoardRepository boardRepository;

  @Test
  void contextLoads() {
  }

//  @Test
//  public void testInsertWithImages() {
//    SpringBoard board = SpringBoard.builder()
//            .boardTitle("imageTest")
//            .boardContent("테스트")
//            .boardWriter("asdf")
//            .build();
//
//    for (int i = 0; i < 3; i++) {
//      board.addFile(UUID.randomUUID().toString(), "file" + i + ".jpg");
//    }
//
//    boardRepository.save(board);
//  }
//
//  @Test
//  public void testReadWithImages() {
//    Optional<SpringBoard> result = boardRepository.findByBoardIdxWithImageSet(53L);
//
//    SpringBoard board = result.orElseThrow();
//
//    System.out.println(board);
//    System.out.println("---------------------");
//    System.out.println("");
//    for (SpringFile springFile : board.getImageSet()) {
//      System.out.println(springFile.getUuid());
//      System.out.println(springFile.getFileNameOriginal());
//      System.out.println(springFile.getFileSize());
//      System.out.println(springFile.getOrd());
//      System.out.println("");
//    }
//  }

//  @Transactional
//  @Commit
//  @Test
//  public void testModifyImages() {
//    Optional<SpringBoard> result = boardRepository.findByBoardIdxWithImageSet(53L);
//    SpringBoard board = result.orElseThrow();
//
//    board.clearImages();
//
//    for (int i = 0; i < 2; i++){
//      board.addFile(UUID.randomUUID().toString(), "updateFile" + i + ".jpg");
//    }
//    boardRepository.save(board);
//  }
}
