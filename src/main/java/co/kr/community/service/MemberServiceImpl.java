package co.kr.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.kr.community.entity.Member;
import co.kr.community.entity.Role;
import co.kr.community.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Member insert(Member member) {
		
		String encodedPassword = passwordEncoder.encode(member.getPassword());
		member.setPassword(encodedPassword);
		
		member.setEnabled(true);
		
		Role role = new Role();
		role.setRoleNo(1l);
		member.getRoles().add(role);
		
		return memberRepository.save(member);
	}

	@Override
	public Page<Member> getMemberList(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.by("regDate"));
		return memberRepository.findAll(pageable);
	}



}
