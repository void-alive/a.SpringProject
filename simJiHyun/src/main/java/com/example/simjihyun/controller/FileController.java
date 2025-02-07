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
    Resource resource = new FileSystemResource(filepath + File.separator + fileNameStored);

    String resourceName = resource.getFilename();
    HttpHeaders headers = new HttpHeaders();

    try {
      headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
    } catch (IOException e) {
      return ResponseEntity.internalServerError().build();
    }
    return ResponseEntity.ok().headers(headers).body(resource);
  }
}
