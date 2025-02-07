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
public class SpringFile{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long fileIdx;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_idx")
  @ToString.Exclude
  @JsonIgnore
  private SpringBoard board;

  @Column(name = "file_name_original")
  private String fileNameOriginal;

  @Column(name = "file_name_stored")
  private String fileNameStored;

  @Column(name = "file_size")
  private long fileSize;
}
