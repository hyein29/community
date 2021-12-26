package co.kr.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.kr.community.entity.Board;
import co.kr.community.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	
	// 닉네임 중복 검사
	List<Member> findByNickname(String nickname);
	
	// 이메일 중복 검사
	List<Member> findByEmail(String email);

}
