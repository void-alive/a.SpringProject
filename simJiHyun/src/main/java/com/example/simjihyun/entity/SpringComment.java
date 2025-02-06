package com.example.simjihyun.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "spring_comment", indexes = {@Index(name = "spring_board_board_idx", columnList = "board_idx")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SpringComment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long commentIdx;

  @Column(nullable = false, name = "comment_id")
  private String commentId;

  @Column(nullable = false, name = "comment_word")
  private String commentWord;

  @Column(nullable = false, name = "comment_date")
  private String commentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_idx")
  @ToString.Exclude
  @JsonIgnore
  private SpringBoard board;
}
