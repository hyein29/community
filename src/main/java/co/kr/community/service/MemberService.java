package co.kr.community.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.kr.community.entity.Member;

public interface MemberService {
	
	// 회원 등록
	Member insert(Member member);

	/* 관리자 페이지 */	
	// 회원 목록 조회
	Page<Member> getMemberList(Pageable pageable);


	/* 회원 가입 시 */
	// 아이디 중복 검사
	String usernameCheck(String username);
	
	// 닉네임 중복 검사
	String nicknameCheck(String nickname);
	
	// 이메일 중복 검사
	String emailCheck(String email);
	
	// 이메일 인증번호 전송
	String sendCode(String email);
	
	
}
