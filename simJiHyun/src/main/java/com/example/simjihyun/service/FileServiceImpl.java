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
//    files 가 null 이거나 길이가 0이면 Collections 의 emptyList() 를 반환함.
    if (files == null || files.length == 0) {
      return Collections.emptyList();
    }

//    SpringFile 타입의 List 를 savedFiles 라는 이름으로 부르기로 함
    List<SpringFile> savedFiles = new ArrayList<>();

//    file 이 없거나 비었다면 넘어감
    for (MultipartFile file : files) {
      if (file == null || file.isEmpty()) {
        continue;
      }

//      . 이후의 값을 extension 으로 저장함
//      이후 saveName 을 랜덤값과 extension 을 더해서 저장함
      String originalName = file.getOriginalFilename();
      String extension = originalName.substring(originalName.lastIndexOf("."));
      String saveName = UUID.randomUUID() + extension;
      long size = file.getSize();

      try {
//        파일의 경로와 이름을 설정하고 실제 파일로 저장
        System.out.println("file path : " + filepath);
        File localFile = new File(filepath + "/" + saveName);
        file.transferTo(localFile);

//        springFile 에 원본 파일명, 저장된 파일명, 파일크기를 넣고 build 함
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
