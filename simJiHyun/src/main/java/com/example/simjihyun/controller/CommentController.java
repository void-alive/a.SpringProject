package com.example.simjihyun.controller;

import com.example.simjihyun.entity.SpringComment;
import com.example.simjihyun.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//    세션에서 memberId 를 가져와 memberId 변수에 넣음
    HttpSession session = request.getSession();
    String memberId = session.getAttribute("memberId").toString();

//    SpringComment 의 memberId 와 commentWord 를 설정함
    SpringComment comment = new SpringComment();
    comment.setCommentId(memberId);
    comment.setCommentWord(commentWord);

//    SpringComment 가 여러개 있는 commentList 에 commentService 에 있는 save 함수 실행함
//    Map : 키와 값을 통한 정보 찾기. 키는 String, 값은 Object 로 정의함
//    result 에 "result" 라는 키로 success 를 넣고,
//    "commentList"라는 키에는 commentList(commentService.save 를 해서 나온 값) 를 넣음
//    그리고 값을 반환함
    List<SpringComment> commentList = commentService.save(boardIdx, comment);
    Map<String, Object> result = new HashMap<>();
    result.put("result", "success");
    result.put("commentList", commentList);
    return result;
  }

  //  댓글 보기
  @GetMapping("/list/{boardIdx}")
  public Object getComments(@PathVariable("boardIdx") long boardIdx) {
    // 모든 댓글 가져오기을 가져와서 result 에 "commentList" 키와 commentList 값을 넣고 반환
    List<SpringComment> commentList = commentService.seeComment(boardIdx);
    Map<String, Object> result = new HashMap<>();
    result.put("commentList", commentList);
    return result;
  }

  //  댓글 수정
  @PutMapping("/modify")
  public void modifyComments(@RequestParam("newCommentWord") String newCommentWord,
                               @RequestParam("commentIdx") long commentIdx,
                               HttpServletRequest request) {
//    세션에서 "memberId" 를 가져와 commentId 라는 변수에 넣음.
//    이후 commentService 의 updateComment 함수 실행
    HttpSession session = request.getSession();
    String commentId = session.getAttribute("memberId").toString();
    commentService.updateComment(newCommentWord, commentId, commentIdx);
  }

  //  댓글 삭제
  @DeleteMapping("/delete")
  public void deleteComments(@RequestParam("commentIdx") long commentIdx) {
    commentService.deleteComment(commentIdx);
  }
}
