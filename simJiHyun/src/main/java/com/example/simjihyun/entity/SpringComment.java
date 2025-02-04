package com.example.simjihyun.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "spring_comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpringComment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long commentIdx;

  @Column(nullable = false, name = "comment_id")
  private String commentId;

  @Column(nullable = false, name = "comment_word")
  private String commentWord;

  @Column(nullable = false, name = "comment_date")
  private LocalDateTime commentDate = now();
}
