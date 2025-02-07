package com.example.simjihyun.controller;

import com.example.simjihyun.service.FileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@RestController
@RequestMapping("/file")
@Log4j2
public class FileController {

  @Value("${file.dir}")
  private String filepath;

  @Autowired
  private FileService fileService;

  //  개별 파일 조회
  @RequestMapping("/view/{fileNameStored}")
  public ResponseEntity<Resource> viewFileGet(@PathVariable("fileNameStored") String fileNameStored) {
//    지정된 파일 경로를 만들고 파일을 리소스로 가져온다
    Resource resource = new FileSystemResource(filepath + File.separator + fileNameStored);

//    파일의 Content-type 을 설정하기 위해 http 헤더 객체를 만든다
    HttpHeaders headers = new HttpHeaders();

//    파일의 타입(image/png, application/pdf) 등을 자동으로 감지해 헤더에 추가한다.
//    못 찾겠다면 500 Internal Server Error 를 반환한다
//    찾았다면 아까 설정한 headers 와 실페 파일 데이터를 body 에 담아온다
    try {
      headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
    } catch (IOException e) {
      return ResponseEntity.internalServerError().build();
    }
    return ResponseEntity.ok().headers(headers).body(resource);
  }
}
