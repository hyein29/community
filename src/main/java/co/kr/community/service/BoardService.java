package co.kr.community.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.kr.community.entity.Board;

public interface BoardService {

	// 게시물 등록
	Board insert(Board board);
	
	// 게시물 목록 조회
	List<Board> getBoardList();
	
	// 게시물 목록 조회(페이징)
	Page<Board> getBoardList(Pageable pageable);
	
	// 조회수 업데이트
	void updateViewCnt(Long bNo);
	
	// 게시물 상세 조회
	Optional<Board> getBoardContent(Long bNo);
	
	// 게시물 수정
	Board update(Board board);
	
	// 게시물 삭제
	void delete(Long bNo);
	
}
