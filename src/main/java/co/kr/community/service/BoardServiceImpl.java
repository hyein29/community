package co.kr.community.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.community.entity.Board;
import co.kr.community.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository boardRepository;
	
	@Override
	public Board insert(Board board) {
		return boardRepository.save(board);
	}

	@Override
	public List<Board> getBoardList() {
		return boardRepository.findAll();
	}

	@Override
	public Optional<Board> getBoardContent(Long b_no) {
		return boardRepository.findById(b_no);
	}

	@Override
	public Board update(Board board) {
		return boardRepository.save(board);
	}
	
	

}
