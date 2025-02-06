package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {

  //  등록
  SpringFile saveFile(MultipartFile file);

  //  파일 엔티티 저장
  void saveFileEntity(SpringFile savedFile);

//  보기

//  삭제
}
