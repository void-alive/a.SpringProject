package com.example.simjihyun.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.LocalDate.now;

@Entity
@Table(name = "spring_board")
@Getter
@NoArgsConstructor
public class SpringBoard {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardIdx;

  @Column(name = "board_title", nullable = false)
  private String boardTitle;

  @Column(name = "board_content", nullable = false)
  private String boardContent;

  @Column(name = "board_writer", nullable = false)
  private String boardWriter;

  @Column(name = "board_date", nullable = false)
  private LocalDateTime boardDate = LocalDateTime.now();

  @Column(name = "hit_cnt", nullable = false)
  private int hitCnt = 0;

}
