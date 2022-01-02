package co.kr.community.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.kr.community.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	
	// 닉네임 중복 검사
	List<Member> findByNickname(String nickname);
	
	// 이메일 중복 검사
	List<Member> findByEmail(String email);
	
	// 회원 탈퇴 시 unreg_date, enabled update
	@Transactional
	@Modifying
	@Query(value = "update member set unreg_date = current_timestamp, enabled = 0 where username = :username", nativeQuery = true)
	void unregister(String username);
	
	// 계정 복구 시 unreg_date, enabled update
	@Transactional
	@Modifying
	@Query(value = "update member set unreg_date = null, enabled = 1 where username = :username", nativeQuery = true)
	void accountRecovery(String username);

	// 관리자 목록 조회
	@Query(value = "select *\r\n"
			+ "  from member m join (select username, count(role_no) as cnt\r\n"
			+ "					   from member_role\r\n"
			+ "					  group by username\r\n"
			+ "					 having cnt > 1) mr\r\n"
			+ "    on m.username = mr.username;", nativeQuery = true)
	List<Member> getOnlyAdminList();
	
	
	// 일반 회원 목록 조회
	@Query(value = "select *\r\n"
			+ "  from member m join (select username, count(role_no) as cnt\r\n"
			+ "					   from member_role\r\n"
			+ "					  group by username\r\n"
			+ "					 having cnt = 1) mr\r\n"
			+ "    on m.username = mr.username;", nativeQuery = true)
	List<Member> getOnlyMemberList();
	
	// 관리자 여부 조회
	@Query(value = "select count(*) from member_role where username = :username and role_no = 2", nativeQuery = true)
	Integer checkRoleAdmin(String username);
	
	// 관리자 권한 설정
	@Transactional
	@Modifying
	@Query(value = "insert into member_role(username, role_no) values(:username, 2)", nativeQuery = true)
	void setRoleAdmin(String username);
	
	// 관리자 권한 해제
	@Transactional
	@Modifying
	@Query(value = "delete from member_role where username = :username and role_no = 2", nativeQuery = true)
	void revokeRoleAdmin(String username);
	
}
