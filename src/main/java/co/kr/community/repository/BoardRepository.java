package co.kr.community.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.kr.community.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	
	// 조회수 업데이트
	@Transactional
	@Modifying
    @Query(value = "update board set b_viewcnt = b_viewcnt + 1 where b_no = :bNo", nativeQuery = true)
    void updateViewCnt(Long bNo);
	
	// 좋아요 여부 체크
	@Query(value = "select count(l_no) from likes where b_no = :bNo and username = :username", nativeQuery = true)
	Integer likesCheck(Long bNo, String username);
	
	// 좋아요 수 조회
	@Query(value = "select count(l_no) from likes where b_no = :bNo", nativeQuery = true)
	Integer likesCntCheck(Long bNo);
	
	// 좋아요 설정
	@Transactional
	@Modifying
	@Query(value = "insert likes(b_no, username) values(:bNo, :username)", nativeQuery = true)
	void insertLike(Long bNo, String username);
	
	// 좋아요 해제
	@Transactional
	@Modifying
	@Query(value = "delete from likes where b_no = :bNo and username = :username", nativeQuery = true)
	void deleteLike(Long bNo, String username);
	
	// 인기 게시판 조회
	@Query(value = "select * \r\n"
			+ "  from board b join (select b_no, count(*) as cnt\r\n"
			+ " 					   from likes\r\n"
			+ " 					  group by b_no) l\r\n"
			+ "    on b.b_no = l.b_no\r\n"
			+ " order by l.cnt desc, b.b_regdate desc\r\n"
			+ " limit 0, 5", 
			countQuery = "select * \r\n"
					+ "  from board b join (select b_no, count(*) as cnt\r\n"
					+ " 					   from likes\r\n"
					+ " 					  group by b_no) l\r\n"
					+ "    on b.b_no = l.b_no\r\n"
					+ " order by l.cnt desc, b.b_regdate desc\r\n"
					+ " limit 0, 5", nativeQuery = true)
	Page<Board> getHotBoardList(Pageable pageable);
	
	// 강아지 게시판 조회
	@Query(value = "select * from board where c_no = 2", countQuery = "select * from board where c_no = 2", nativeQuery = true)
	Page<Board> getDogBoardList(Pageable pageable);
	
	// 고양이 게시판 조회
	@Query(value = "select * from board where c_no = 1", countQuery = "select * from board where c_no = 1", nativeQuery = true)
	Page<Board> getCatBoardList(Pageable pageable);

}
