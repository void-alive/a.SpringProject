package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringFile;
import com.example.simjihyun.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
  @Autowired
  private FileRepository fileRepository;

  @Value("${file.dir}")
  private String filepath;

  //  등록
  @Override
  public SpringFile saveFile(MultipartFile file) {
    if (file == null || file.isEmpty()) {
      return null;
    }

    String saveName = UUID.randomUUID().toString();
    long size = file.getSize();

    try {
      System.out.println("file path : " + filepath);
      File localFile = new File(filepath + "/" + saveName);
      file.transferTo(localFile);

      SpringFile springFile = SpringFile.builder()
              .fileNameStored(saveName)
              .fileSize(size)
              .build();

      System.out.println("파일 저장 완료 : " + localFile.getAbsolutePath());
      return springFile;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  //파일 엔티티 저장
  @Override
  public void saveFileEntity(SpringFile file) {
    fileRepository.save(file);
  }

  //  보기

  //  삭제

}
