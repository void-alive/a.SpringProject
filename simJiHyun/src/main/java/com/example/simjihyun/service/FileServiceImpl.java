package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringFile;
import com.example.simjihyun.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
  @Autowired
  private FileRepository fileRepository;

  @Value("${file.dir}")
  private String filepath;

  //  첨부파일 여러개 등록
  @Override
  public List<SpringFile> saveFiles(MultipartFile[] files) {
    if (files == null || files.length == 0) {
      return Collections.emptyList();
    }

    List<SpringFile> savedFiles = new ArrayList<>();

    for (MultipartFile file : files) {
      if (file == null || file.isEmpty()) {
        continue;
      }

      String originalName = file.getOriginalFilename();
      String extension = originalName.substring(originalName.lastIndexOf("."));
      String saveName = UUID.randomUUID().toString() + extension;
      long size = file.getSize();

      try {
        System.out.println("file path : " + filepath);
        File localFile = new File(filepath + "/" + saveName);
        file.transferTo(localFile);

        SpringFile springFile = SpringFile.builder()
                .fileNameOriginal(originalName)
                .fileNameStored(saveName)
                .fileSize(size)
                .build();

        savedFiles.add(springFile);
        System.out.println("파일 저장 완료 : " + localFile.getAbsolutePath());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    return savedFiles;
  }

  //  파일 엔티티 저장
  @Override
  public void saveFileEntity(SpringFile file) {
    fileRepository.save(file);
  }

  //  보기
  @Override
  public List<SpringFile> findAllFile(long boardIdx) {
    return fileRepository.findByBoardBoardIdx(boardIdx);
  }

}
