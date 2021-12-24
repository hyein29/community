package co.kr.community.service;

import java.util.HashMap;
import java.util.List;

import co.kr.community.entity.Comment;

public interface CommentService {

	// 댓글 등록
	Comment insert(Comment comment);
	
	// 댓글 조회
	List<Comment> getCommentList(Long bNo);
	
	// 마지막 group 조회
	Integer getLastCommentGroup(Long bNo);
	
	// 특정 group의 마지막 sequence 조회
	Integer getLastCommentSequence(Long bNo, int cmGrp);
	
	// 댓글 삭제
	void delete(HashMap<String, Object> comment);
	
}
