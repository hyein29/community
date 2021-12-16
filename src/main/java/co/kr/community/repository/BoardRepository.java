package co.kr.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.kr.community.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
