package co.kr.community.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.kr.community.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
//	List<Comment> findAllByBNo(Long bNo);
	
    @Query(value = "select * from comment where b_no = :bNo order by cm_grp", nativeQuery = true)
//    @Query(value = "select cm_no, b_no, cm_grp, cm_seq, cm_content, cm_writer from comment where b_no = :bNo", nativeQuery = true)
    List<Comment> getCommentListByBNoOrderByCmGrp(Long bNo);

    @Query(value = "select max(cm_grp) as cm_grp from comment where b_no = :bNo", nativeQuery = true)
    Integer getLastCommentGroup(Long bNo);
	
}
