package com.example.simjihyun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UploadResultDTO {
  private String uuid;
  private String fileName;
  private boolean img;

  public String getLink() {
    if (img) {
      return "s_" + uuid + "_" + fileName;
    } else {
      return uuid + "_" + fileName;
    }
  }

  private Long boardIdx;
}
