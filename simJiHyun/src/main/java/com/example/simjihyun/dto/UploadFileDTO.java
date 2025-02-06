package com.example.simjihyun.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadFileDTO {
  private List<MultipartFile> files;
}
