package co.kr.community.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import co.kr.community.entity.Board;
import co.kr.community.entity.Member;
import co.kr.community.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository boardRepository;
	
	@Override
	public String insert(Board board, Principal principal) {
		String username = principal.getName();
		Member member = new Member();
		member.setUsername(username);
		board.setMember(member);
		boardRepository.save(board);
		return "success";
	}

	@Override
	public List<Board> getBoardList() {
		return boardRepository.findAll();
	}
	
//	@Override
//	public Page<Board> getBoardList(Pageable pageable) {
//		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
//		pageable = PageRequest.of(page, 10, Sort.by("bNo").descending());
//		return boardRepository.findAll(pageable);
//	}
	
	// 전체 게시판 조회
	@Override
	public Page<Board> getTotalBoardList(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.by("bNo").descending());
		return boardRepository.findAll(pageable);
	}
	
	// 인기 게시판 조회
	@Override
	public Page<Board> getHotBoardList(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.by("b_no").descending());
		return boardRepository.getHotBoardList(pageable);
	}
	
	// 강아지 게시판 조회
	@Override
	public Page<Board> getDogBoardList(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.by("b_no").descending());
		return boardRepository.getDogBoardList(pageable);
	}
	
	// 고양이 게시판 조회
	@Override
	public Page<Board> getCatBoardList(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.by("b_no").descending());
		return boardRepository.getCatBoardList(pageable);
	}
	
	// 검색 기능
	@Override
	public Page<Board> getTotalBoardSearchList(Pageable pageable, String boardName, String searchOpt, String searchVal) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.by("b_no").descending());
		
		if(boardName == "total") {
			return boardRepository.findByBTitleContaining(searchVal, pageable);
		}else {

			return boardRepository.findByBTitleContaining(searchVal, pageable);
			
		}
		
	}
	
	
	
	// 조회수 업데이트
	@Override
	public void updateViewCnt(Long bNo) {
		boardRepository.updateViewCnt(bNo);		
	}	

	// 게시물 조회
	@Override
	public Optional<Board> getBoardContent(Long bNo) {
		return boardRepository.findById(bNo);
	}

	// 게시물 수정
	@Override
	public String update(Board board) {
		boardRepository.save(board);
		return "success";
	}

	// 게시물 삭제
	@Override
	public void delete(Long bNo) {
		boardRepository.deleteById(bNo);
	}

	// 좋아요 여부 체크
	@Override
	public String likesCheck(Long bNo, Principal principal) {
		
		if(principal == null) { // 비회원인 경우 빈하트 출력
			return "false";
		}else {
			String username = principal.getName();
			
			if(boardRepository.likesCheck(bNo, username) == 1) {
				return "true";
			}else {
				return "false";
			}
		}
		
		
		
	}
	
	// 좋아요 수 조회
	@Override
	public String likesCntCheck(Long bNo) {
		
		if(boardRepository.likesCntCheck(bNo) != null) {
			String likesCnt = Integer.toString(boardRepository.likesCntCheck(bNo));
			return likesCnt;
		}else {
			return "0";
		}
		
	}

	// 좋아요 설정
	@Override
	public String insertLike(Long bNo, Principal principal) {
		
		String username = principal.getName();
		boardRepository.insertLike(bNo, username);
		
		return "success";
	}

	
	// 좋아요 해제
	@Override
	public String deleteLike(Long bNo, Principal principal) {
		
		String username = principal.getName();
		boardRepository.deleteLike(bNo, username);
		
		return "success";
	}

	

}
