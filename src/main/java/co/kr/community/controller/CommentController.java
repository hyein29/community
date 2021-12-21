package co.kr.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.community.entity.Comment;
import co.kr.community.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@PostMapping("/comment")
	@ResponseBody
	public String write(@RequestBody Comment comment) {
		System.out.println("=======================" + comment.toString());
		String result = "success";
		return result;
	}


}
