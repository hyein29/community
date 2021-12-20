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
    @Query("update Board set bViewcnt = bViewcnt + 1 where bNo = :bNo")
    void updateViewCnt(Long bNo);

}
