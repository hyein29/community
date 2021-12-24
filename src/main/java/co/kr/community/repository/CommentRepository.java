package co.kr.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.kr.community.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	// 게시물 별 댓글 목록 group 순으로 조회
    @Query(value = "select * from comment where b_no = :bNo order by cm_grp", nativeQuery = true)
    List<Comment> getCommentListByBNoOrderByCmGrp(Long bNo);

    @Query(value = "select max(cm_grp) as cm_grp from comment where b_no = :bNo", nativeQuery = true)
    Integer getLastCommentGroup(Long bNo);
    
    @Query(value = "select max(cm_seq) as cm_seq from comment where b_no = :bNo and cm_grp = :cmGrp", nativeQuery = true)
    Integer getLastCommentSequence(Long bNo, int cmGrp);
    
    @Transactional
    @Modifying
    @Query(value = "delete from comment where b_no = :bNo and cm_grp = :cmGrp", nativeQuery = true)
    void deleteComment(Long bNo, int cmGrp);
	
}
