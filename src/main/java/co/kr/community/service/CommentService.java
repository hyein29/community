package co.kr.community.service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import co.kr.community.entity.Comment;

public interface CommentService {

	// 댓글 등록
	Comment insertComment(HashMap<String, Object> comment, Principal principal);
	
	// 대댓글 등록
	Comment insertReply(HashMap<String, Object> comment, Principal principal);
	
	// 댓글 조회
	List<Comment> getCommentList(Long bNo);

	// 댓글 삭제
	void delete(HashMap<String, Object> comment);
	
	
	
}
