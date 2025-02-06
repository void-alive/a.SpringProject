package com.example.simjihyun.dto;

import com.example.simjihyun.entity.SpringComment;
import com.example.simjihyun.entity.SpringFile;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class SpringBoardDTO {
  private Long boardIdx;
  private String boardTitle;
  private String boardContent;
  private String boardWriter;
  private String boardDate;
  private int hitCnt;
  private List<SpringComment> comments;
  private List<SpringFile> fileNames;
}