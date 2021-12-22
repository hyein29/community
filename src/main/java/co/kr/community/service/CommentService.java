package co.kr.community.service;

import java.util.List;
import java.util.Optional;

import co.kr.community.entity.Comment;

public interface CommentService {

	// 댓글 등록
	Comment insert(Comment comment);
	
	// 댓글 조회
	List<Comment> getCommentList(Long bNo);
	
	// 댓글 group number 조회
	Integer getLastCommentGroup(Long bNo);
	
}
