package co.kr.community.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.kr.community.entity.Board;

public interface BoardService {

	Board insert(Board board);
	List<Board> getBoardList();
	Page<Board> getBoardList(Pageable pageable);
	void updateViewCnt(Long bNo);
	Optional<Board> getBoardContent(Long bNo);
	Board update(Board board);
	void delete(Long bNo);
	
}
