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
	
	
}
