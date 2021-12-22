package co.kr.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.community.entity.Comment;
import co.kr.community.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public Comment insert(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> getCommentList(Long bNo) {
		return commentRepository.getCommentListByBNoOrderByCmGrp(bNo);
	}

	@Override
	public Integer getLastCommentGroup(Long bNo) {
		return commentRepository.getLastCommentGroup(bNo);
	}

	

}
