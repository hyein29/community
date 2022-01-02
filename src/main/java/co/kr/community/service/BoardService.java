package co.kr.community.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.kr.community.entity.Board;

public interface BoardService {

	// 게시물 등록
	String insert(Board board, Principal principal);
	
	// 게시물 목록 조회
	List<Board> getBoardList();
	
	// 게시물 목록 조회(페이징)
//	Page<Board> getBoardList(Pageable pageable);
	
	// 게시판 조회
	Page<Board> getTotalBoardList(Pageable pageable); // 전체 게시판
	Page<Board> getHotBoardList(Pageable pageable); // 인기 게시판
	Page<Board> getDogBoardList(Pageable pageable); // 강아지 게시판
	Page<Board> getCatBoardList(Pageable pageable); // 고양이 게시판
	
	// 검색 기능
	Page<Board> getTotalBoardSearchList(Pageable pageable, 
			String boardName, String searchOpt, String searchVal);
	
	// 조회수 업데이트
	void updateViewCnt(Long bNo);
	
	// 게시물 상세 조회
	Optional<Board> getBoardContent(Long bNo);
	
	// 게시물 수정
	String update(Board board);
	
	// 게시물 삭제
	void delete(Long bNo);
	
	// 게시물 좋아요 조회
	String likesCheck(Long bNo, Principal principal);
	
	// 게시물 좋아요 조회
	String likesCntCheck(Long bNo);
	
	// 게시물 좋아요 설정
	String insertLike(Long bNo, Principal principal);
	
	// 게시물 좋아요 해제
	String deleteLike(Long bNo, Principal principal);
	
}
