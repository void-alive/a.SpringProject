package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringBoard;

import java.util.List;

public interface BoardService {

  List<SpringBoard> query() throws Exception;
}
