package co.kr.community.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.community.entity.Board;
import co.kr.community.entity.Comment;
import co.kr.community.entity.Member;
import co.kr.community.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@PostMapping("/comment")
	@ResponseBody
	public String write(@RequestBody HashMap<String, Object> comment) {
		System.out.println("comment 도입부");
		System.out.println("=======================" + comment);

		Long bNo = Long.parseLong(comment.get("bNo").toString());
		String username = comment.get("username").toString();
		String cmContent = comment.get("cmContent").toString();
//		int cmGrp = Integer.parseInt(comment.get("cmGrp").toString());
//		int cmSeq = Integer.parseInt(comment.get("cmSeq").toString());
		
		Comment insertComment = new Comment();
		
		Board board = new Board();
		board.setBNo(bNo);
		insertComment.setBoard(board);
		
		Member member = new Member();
		member.setUsername(username);
		insertComment.setMember(member);
		
		int lastCmGrp = 0;
		
		if(commentService.getLastCommentGroup(bNo) != null) {
			lastCmGrp = commentService.getLastCommentGroup(bNo);
		}
		
		insertComment.setCmContent(cmContent);
		insertComment.setCmGrp(lastCmGrp + 1);
		insertComment.setCmSeq(1);
		
		System.out.println(insertComment);
		
		commentService.insert(insertComment);
		
		String result = "success";
		return result;
	}
	
//	@GetMapping("/comment/{bNo}")
//	@ResponseBody
//	public Optional<Comment> getCommentList(@PathVariable("bNo") Long bNo) {
//		System.out.println(bNo);
//		return commentService.getCommentList(bNo);
//	}


}
