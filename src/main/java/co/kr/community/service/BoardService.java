package co.kr.community.service;

import java.util.List;
import java.util.Optional;

import co.kr.community.entity.Board;

public interface BoardService {

	Board insert(Board board);
	List<Board> getBoardList();
	Optional<Board> getBoardContent(Long b_no);
	
}
