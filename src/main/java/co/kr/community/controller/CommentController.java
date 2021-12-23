package co.kr.community.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@PostMapping("/comment")
	@ResponseBody
	public String write(@RequestBody HashMap<String, Object> comment, Principal principal) {
		System.out.println("comment 도입부");
		System.out.println("=======================" + comment);

		Long bNo = Long.parseLong(comment.get("bNo").toString());
		String username = principal.getName();
		String cmContent = comment.get("cmContent").toString();
		
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
	
	@GetMapping("/comment/{bNo}")
	@ResponseBody
	public List<Comment> getCommentList(@PathVariable("bNo") Long bNo) {
		System.out.println(bNo);
		return commentService.getCommentList(bNo);
	}

	
	@PostMapping("/comment/reply")
	@ResponseBody
	public String writeReply(@RequestBody HashMap<String, Object> comment, Principal principal) {
		System.out.println("reply 도입부");
		System.out.println("=======================" + comment);

		String username = principal.getName();
		Long bNo = Long.parseLong(comment.get("bNo").toString());
		String cmContent = comment.get("cmContent").toString();
		int cmGrp = Integer.parseInt(comment.get("cmGrp").toString());
		
		Comment insertReply = new Comment();
		
		Board board = new Board();
		board.setBNo(bNo);
		insertReply.setBoard(board);
		
		Member member = new Member();
		member.setUsername(username);
		insertReply.setMember(member);
		
		
		int lastCmSeq = 1;
		
		if(commentService.getLastCommentSequence(bNo, cmGrp) != 1) {
			lastCmSeq = commentService.getLastCommentSequence(bNo, cmGrp);
		}
		
		insertReply.setCmContent(cmContent);
		insertReply.setCmGrp(cmGrp);
		insertReply.setCmSeq(lastCmSeq + 1);
		
		System.out.println(insertReply);
		
		commentService.insert(insertReply);
		
		String result = "success";
		return result;
	}
	
	// 댓글 삭제
	@DeleteMapping("/comment/{cmNo}")
	@ResponseBody
	public String delete(@PathVariable("cmNo") Long cmNo) {
		System.out.println("cmNo ======================> " + cmNo);
		
		return "redirect:/board";
	}
	
}
