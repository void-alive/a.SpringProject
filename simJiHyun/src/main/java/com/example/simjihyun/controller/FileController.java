package com.example.simjihyun.controller;

import com.example.simjihyun.dto.UploadFileDTO;
import com.example.simjihyun.dto.UploadResultDTO;
import com.example.simjihyun.service.FileService;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/file")
@Log4j2
public class FileController {

  @Value("${file.dir}")
  private String filepath;

  final List<UploadResultDTO> list = new ArrayList<>();

  @Autowired
  private FileService fileService;

  //  파일 등록
  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO) {
    List<UploadResultDTO> uploadFiles = new ArrayList<>();

    if (uploadFileDTO.getFiles() != null) {
      uploadFileDTO.getFiles().forEach(multipartfile -> {
        String originalName = multipartfile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        Path savePath = Paths.get(filepath, uuid + "_" + originalName);
        boolean image = originalName.toLowerCase().matches(".*\\.(jpg|jpeg|png|gif|bmp)$");

        try {
          multipartfile.transferTo(savePath);
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println(e.getMessage());
        }

        uploadFiles.add(UploadResultDTO.builder()
                .uuid(uuid)
                .fileName(originalName)
                .img(image)
                .boardIdx(null)
                .build());
      });
//      end each
      list.addAll(uploadFiles);
      return uploadFiles;
    }
    return null;
  }

  // 2. 게시글 생성 후 파일을 boardIdx에 연결
  @PostMapping("/associate/{boardIdx}")
  public Map<String, String> associateFilesWithBoard(@PathVariable("boardIdx") Long boardIdx) {
    for (UploadResultDTO file : list) {
      if (file.getBoardIdx() == null) {
        file.setBoardIdx(boardIdx);  // 파일을 해당 게시글과 연결
      }
    }
    return Collections.singletonMap("message", "파일이 게시글과 연결되었습니다.");
  }

  //  개별 파일 조회
  @GetMapping("/view/{fileName}")
  public ResponseEntity<Resource> viewFileGet(@PathVariable("fileName") String fileName) {
    Resource resource = new FileSystemResource(filepath + File.separator + fileName);

    String resourceName = resource.getFilename();
    HttpHeaders headers = new HttpHeaders();

    try {
      headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
    } catch (IOException e) {
      return ResponseEntity.internalServerError().build();
    }
    return ResponseEntity.ok().headers(headers).body(resource);
  }

  // 모든 파일 조회
  @GetMapping("/list/{boardIdx}")
  public List<UploadResultDTO> getFilesByBoard(@PathVariable("boardIdx") long boardIdx) {
    List<UploadResultDTO> boardFiles = new ArrayList<>();

    File dir = new File(filepath);
    File[] files = dir.listFiles();

    if (files != null) {
      for (File file : files) {
        if (file.isFile()) {
          String fileName = file.getName();
          boolean isImage = fileName.toLowerCase().matches(".*\\.(jpg|jpeg|png|gif|bmp)$");

          boardFiles.add(UploadResultDTO.builder()
                  .uuid("")
                  .fileName(fileName)
                  .img(isImage)
                  .build());
        }
      }
    }

    for (UploadResultDTO file : list) {
      if (Objects.equals(boardIdx, file.getBoardIdx())) {
        boardFiles.add(file);
      }
    }

    return boardFiles;
  }

  //  삭제
  @DeleteMapping("/remove/{fileName}")
  public Map<String, Boolean> removeFile(@PathVariable("fileName") String fileName) {
    Resource resource = new FileSystemResource(filepath + File.separator + fileName);
    String resourceName = resource.getFilename();

    Map<String, Boolean> resultMap = new HashMap<>();
    boolean removed = false;

    try {
      String contentType = Files.probeContentType(resource.getFile().toPath());
      removed = resource.getFile().delete();

      if (contentType.startsWith("image")) {
        File thumbFile = new File(filepath + File.separator + "s_" + fileName);
        thumbFile.delete();
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    resultMap.put("removed", removed);
    return resultMap;
  }
}
