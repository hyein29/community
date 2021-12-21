package co.kr.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.kr.community.entity.Board;
import co.kr.community.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
}
