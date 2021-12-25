package co.kr.community.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.kr.community.entity.Board;
import co.kr.community.entity.Comment;
import co.kr.community.entity.Member;
import co.kr.community.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	CommentService commentService;
	
	// 댓글 등록
	@PostMapping("/comment")
	@ResponseBody
	public String write(@RequestBody HashMap<String, Object> comment, Principal principal) {
		commentService.insertComment(comment, principal);
		String result = "success";
		return result;
	}
	
	// 댓글 조회
	@GetMapping("/comment/{bNo}")
	@ResponseBody
	public List<Comment> getCommentList(@PathVariable("bNo") Long bNo) {
		System.out.println(bNo);
		return commentService.getCommentList(bNo);
	}

	// 대댓글 등록
	@PostMapping("/comment/reply")
	@ResponseBody
	public String writeReply(@RequestBody HashMap<String, Object> comment, Principal principal) {
		commentService.insertReply(comment, principal);
		String result = "success";
		return result;
	}
	
	// 댓글 삭제
	@DeleteMapping("/comment")
	@ResponseBody
	public String delete(@RequestBody HashMap<String, Object> comment) {
		commentService.delete(comment);
		String result = "success";
		return result;
	}
	
}
