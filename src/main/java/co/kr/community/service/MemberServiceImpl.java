package co.kr.community.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
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
	
	@Autowired
	JavaMailSender javaMailSender; // 이메일 전송 bean

	// 회원 가입
	@Override
	public Member insert(Member member) {
		
		// 비밀번호 암호화
		String encodedPassword = passwordEncoder.encode(member.getPassword()); 
		member.setPassword(encodedPassword);
		
		// 활성화 여부
		member.setEnabled(true);
		
		// 멤버 권한 설정
		Role role = new Role();
		role.setRoleNo(1l);
		member.getRoles().add(role);
		
		return memberRepository.save(member);
	}
	
	// 내 정보 조회
	@Override
	public Optional<Member> getMyInfo(String username) {
		return memberRepository.findById(username);
	}

	// 회원 탈퇴
	@Override
	public void unregister(String username) {
		memberRepository.unregister(username);
	}
	
	// 회원 목록 조회(페이징)
//	@Override
//	public Page<Member> getMemberList(Pageable pageable) {
//		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
//		pageable = PageRequest.of(page, 10, Sort.by("regDate"));
//		return memberRepository.findAll(pageable);
//	}
	
	// 회원 목록 조회 (전체)
	@Override
	public List<Member> getMemberList() {
		return memberRepository.findAll();
	}
	
	// 회원 목록 조회 (관리자만)
	@Override
	public List<Member> getOnlyAdminList() {
		return memberRepository.getOnlyAdminList();
	}
	
	// 회원 목록 조회 (일반회원만)
	@Override
	public List<Member> getOnlyMemberList() {
		return memberRepository.getOnlyMemberList();
	}
	
	
	
	/* 회원 가입 시 */
	// 아이디 중복 검사
	@Override
	public String usernameCheck(String username) {
		
		if(!memberRepository.findById(username).isEmpty()) {
			return "exist";
		}else {
			return "notExist";
		}
	}
	
	// 닉네임 중복 검사
	@Override
	public String nicknameCheck(String nickname) {
		
		if(!memberRepository.findByNickname(nickname).isEmpty()) {
			return "exist";
		}else {
			return "notExist";
		}
	}

	// 이메일 중복 검사
	@Override
	public String emailCheck(String email) {
		
		if(!memberRepository.findByEmail(email).isEmpty()) {
			return "exist";
		}else {
			return sendCode(email);
		}
	}
	
	
	
	// 이메일 인증번호 전송
	@Override
	public String sendCode(String email) {
		
		System.out.println("이메일 전송 service impl ~~~~~~~~~~~~~~~~~~");
		System.out.println("이메일 =============> " + email);
		
		// 인증번호 난수 생성
		Random random = new Random(); 
		int authCode = random.nextInt(899999) + 100000; // 100000 ~ 999999 범위의 난수 생성
		System.out.println("인증번호 =============> " + authCode);
		
		String title = "커뮤니티 : 회원가입 인증";
		String content = "커뮤니티를 이용해주셔서 감사합니다." + "<br><br>" + "인증번호는 <b>" + authCode + "</b> 입니다." + "<br><br>"
				+ "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

		
		// 메일 발송
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email, "UTF-8")); // 수신자 설정
			message.setSubject(title); // 메일 제목
			message.setText(content); // 메일 내용
			javaMailSender.send(message); // 메일 발송
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String num = Integer.toString(authCode); // view단으로 보내기 위한 난수 String 파싱

		return num;
	}

	
	/* 회원 정보 수정 */
	// 회원 정보 수정 시 입력한 비밀번호와 기존 비밀번호 비교
	@Override
	public String passwordCheck(String password, String username) {
		
		String oldPassword = memberRepository.findById(username).get().getPassword();
		
		System.out.println(oldPassword);
		
		if(passwordEncoder.matches(password, oldPassword)) {
			return "true"; // 일치하는 경우(해당 비밀번호 사용 불가)
		}else {
			return "false"; // 일치하지 않는 경우
		}
		
	}

	// 회원 정보 수정
	@Override
	public Member update(Member member) {
		
		// 비밀번호 암호화
		String encodedPassword = passwordEncoder.encode(member.getPassword()); 
		member.setPassword(encodedPassword);
		
		return memberRepository.save(member);
	}

	
	// 계정 복구
	@Override
	public void accountRecovery(String username) {
		
		memberRepository.accountRecovery(username);
		
	}

	// 권한 변경
	@Override
	public void modifyAuthority(String username) {
		
		if(memberRepository.checkRoleAdmin(username) < 1) { // 관리자 권한이 없는 경우
			memberRepository.setRoleAdmin(username); // 관리자 권한 설정
		}else { // 이미 관리자인 경우
			memberRepository.revokeRoleAdmin(username); // 관리자 권한 해제
		}
				
	}
	

	
	
	
	



}
