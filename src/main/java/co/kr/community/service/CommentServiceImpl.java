package co.kr.community.service;

import java.security.Principal;
import java.util.HashMap;
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

	@Override
	public Integer getLastCommentSequence(Long bNo, int cmGrp) {
		return commentRepository.getLastCommentSequence(bNo, cmGrp);
	}

	@Override
	public void delete(HashMap<String, Object> comment) {
		Long bNo = Long.parseLong(comment.get("bNo").toString());
		Long cmNo = Long.parseLong(comment.get("cmNo").toString());
		int cmGrp = Integer.parseInt(comment.get("cmGrp").toString());
		int cmSeq = Integer.parseInt(comment.get("cmSeq").toString());
		
		if(cmSeq == 1) {
			commentRepository.deleteComment(bNo, cmGrp);
		}else {
			commentRepository.deleteById(cmNo);
		}
		
	}
	
	
	

}
