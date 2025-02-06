package com.example.simjihyun.controller;

import com.example.simjihyun.entity.SpringBoard;
import com.example.simjihyun.entity.SpringComment;
import com.example.simjihyun.service.CommentService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
@Log4j2
public class CommentController {

  @Autowired
  private CommentService commentService;

  //  댓글 작성
  @ResponseBody
  @PostMapping("/write/{boardIdx}")
  public Object comment(@PathVariable("boardIdx") long boardIdx,
                        @RequestParam("commentWord") String commentWord,
                        HttpServletRequest request) throws Exception {

    HttpSession session = request.getSession();
    String memberId = session.getAttribute("memberId").toString();

    SpringComment comment = new SpringComment();
    comment.setCommentId(memberId);
    comment.setCommentWord(commentWord);

    List<SpringComment> commentList = commentService.save(boardIdx, comment);
    Map<String, Object> result = new HashMap<>();
    result.put("result", "success");
    result.put("commentList", commentList);
    return result;
  }

  //  댓글 보기
  @GetMapping("/list/{boardIdx}")
  public Object getComments(@PathVariable("boardIdx") long boardIdx) {
    // 모든 댓글 가져오기
    List<SpringComment> commentList = commentService.seeComment(boardIdx);
    Map<String, Object> result = new HashMap<>();
    result.put("commentList", commentList);
    return result;
  }

  //  댓글 수정
  @PutMapping("/modify")
  public Object modifyComments(@RequestParam("newCommentWord") String newCommentWord,
                             @RequestParam("commentIdx") long commentIdx,
                             HttpServletRequest request) {
    HttpSession session = request.getSession();
    String commentId = session.getAttribute("memberId").toString();
    System.out.println(commentId);

    commentService.updateComment(newCommentWord, commentId, commentIdx);
    Map<String,Object> result = new HashMap<>();
    result.put("result", "success");
    return result;
  }

  //  댓글 삭제
  @DeleteMapping("/delete")
  public Object deleteComments(@RequestParam("commentIdx") long commentIdx) {
    commentService.deleteComment(commentIdx);
    Map<String, Object> result = new HashMap<>();
    result.put("result", "success");
    return result;
  }
}
