package com.example.simjihyun.service;

import com.example.simjihyun.entity.SpringComment;
import com.example.simjihyun.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentRepository commentRepository;

  @Override
  public void saveComment(SpringComment comment) {
    commentRepository.save(comment);
  }
}
