package com.example.simjihyun.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.time.LocalDate.now;

@Entity
@Table(name = "spring_board")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
  @Builder.Default
  private String boardDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

  @Column(name = "hit_cnt", nullable = false)
  @Builder.Default
  private int hitCnt = 0;

  @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @ToString.Exclude
  @Builder.Default
  private List<SpringComment> comments = new ArrayList<>();

//  @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//  @ToString.Exclude
//  private List<SpringFile> files = new ArrayList<>();

  @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @ToString.Exclude
  @Builder.Default
  private List<SpringFile> fileNames = new ArrayList<>();
}
