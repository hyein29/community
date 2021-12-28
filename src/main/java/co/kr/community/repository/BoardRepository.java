package co.kr.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.kr.community.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	
	@Transactional
	@Modifying
    @Query(value = "update Board set b_viewcnt = b_viewcnt + 1 where b_no = :bNo", nativeQuery = true)
    void updateViewCnt(Long bNo);
	
	@Query(value = "select count(l_no) from likes where b_no = :bNo and username = :username", nativeQuery = true)
	Integer likesCheck(Long bNo, String username);
	
	@Query(value = "select count(l_no) from likes where b_no = :bNo", nativeQuery = true)
	Integer likesCntCheck(Long bNo);
		
	@Transactional
	@Modifying
	@Query(value = "insert likes(b_no, username) values(:bNo, :username)", nativeQuery = true)
	void insertLike(Long bNo, String username);
	
	@Transactional
	@Modifying
	@Query(value = "delete from likes where b_no = :bNo and username = :username", nativeQuery = true)
	void deleteLike(Long bNo, String username);
	
	
	

}
