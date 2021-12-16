package co.kr.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.kr.community.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
