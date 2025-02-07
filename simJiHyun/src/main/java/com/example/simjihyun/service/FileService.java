package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface FileService {
  //  첨부파일 여러개 등록
  List<SpringFile> saveFiles(MultipartFile[] files);

  //  파일 엔티티 저장
  void saveFileEntity(SpringFile savedFile);

  //  보기
  List<SpringFile> findAllFile(long boardIdx);
}
