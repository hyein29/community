package co.kr.community.service;

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
import co.kr.community.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository boardRepository;
	
	@Override
	public String insert(Board board) {
		boardRepository.save(board);
		return "success";
	}

	@Override
	public List<Board> getBoardList() {
		return boardRepository.findAll();
	}
	
	@Override
	public Page<Board> getBoardList(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.by("bNo").descending());
		return boardRepository.findAll(pageable);
	}
	
	@Override
	public void updateViewCnt(Long bNo) {
		boardRepository.updateViewCnt(bNo);		
	}	

	@Override
	public Optional<Board> getBoardContent(Long bNo) {
		return boardRepository.findById(bNo);
	}

	@Override
	public String update(Board board) {
		boardRepository.save(board);
		return "success";
	}

	@Override
	public void delete(Long bNo) {
		boardRepository.deleteById(bNo);
	}

	

}
