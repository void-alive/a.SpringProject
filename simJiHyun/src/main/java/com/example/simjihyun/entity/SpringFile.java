package com.example.simjihyun.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "spring_file")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class SpringFile implements Comparable<SpringFile> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long fileIdx;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_idx")
  @ToString.Exclude
  @JsonIgnore
  private SpringBoard board;

  @Column(name = "file_name_stored")
  private String fileNameStored;

  @Column(name = "file_size")
  private long fileSize;

  @Column(name = "upload_member")
  private String uploadMember;

  @Column
  private int ord;

  @Override
  public int compareTo(SpringFile other) {
    return this.ord - other.ord;
  }

  public void changeBoard(SpringBoard board) {
    this.board = board;
  }
}
