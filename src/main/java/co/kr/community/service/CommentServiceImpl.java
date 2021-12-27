package co.kr.community.service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.community.entity.Board;
import co.kr.community.entity.Comment;
import co.kr.community.entity.Member;
import co.kr.community.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public String insertComment(HashMap<String, Object> comment, Principal principal) {
		System.out.println("========Impl insertComment 도입부=========");
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
		
		if(commentRepository.getLastCommentGroup(bNo) != null) {
			lastCmGrp = commentRepository.getLastCommentGroup(bNo);
		}
		
		insertComment.setCmContent(cmContent);
		insertComment.setCmGrp(lastCmGrp + 1);
		insertComment.setCmSeq(1);
		
		System.out.println(insertComment);
	
		commentRepository.save(insertComment);
		
		return "success";
	}
	
	@Override
	public String insertReply(HashMap<String, Object> comment, Principal principal) {
		System.out.println("========Impl insertReply 도입부=========");
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
		
		if(commentRepository.getLastCommentSequence(bNo, cmGrp) != 1) {
			lastCmSeq = commentRepository.getLastCommentSequence(bNo, cmGrp);
		}
		
		insertReply.setCmContent(cmContent);
		insertReply.setCmGrp(cmGrp);
		insertReply.setCmSeq(lastCmSeq + 1);
		
		System.out.println(insertReply);
		
		commentRepository.save(insertReply);
		
		return "success";
	}

	@Override
	public List<Comment> getCommentList(Long bNo) {
		return commentRepository.getCommentListByBNoOrderByCmGrp(bNo);
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
