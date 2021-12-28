package co.kr.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.community.entity.Member;
import co.kr.community.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberValidationController {

	@Autowired
	MemberService memberService;

	@Autowired
	JavaMailSender javaMailSender;
	
	// 아이디 중복 검사
	@PostMapping("/register/usernameCheck")
	@ResponseBody
	public String usernameCheck(String username) {
		System.out.println("====validationController 아이디 중복 검사 진입==========");
		
		return memberService.usernameCheck(username);
	}
	
	// 닉네임 중복 검사
	@PostMapping("/register/nicknameCheck")
	@ResponseBody
	public String nicknameCheck(String nickname) {
		System.out.println("====validationController 닉네임 중복 검사 진입==========");
		
		return memberService.nicknameCheck(nickname);
	}
	
	// 이메일 중복 검사
	@PostMapping("/register/emailCheck")
	@ResponseBody
	public String emailCheck(String email) {
		System.out.println("====validationController 이메일 중복 검사 진입==========");
		
		return memberService.emailCheck(email);
	}
	
	// 이메일 인증번호 전송
	@PostMapping("/register/sendCode")
	@ResponseBody
	public String sendCode(String email) {
		System.out.println("================sendEmail 진입===============");
		return memberService.sendCode(email);
	}
	
	// 회원 정보 수정 시 입력한 비밀번호와 기존 비밀번호 비교
	@PostMapping("/register/passwordCheck")
	@ResponseBody
	public String passwordCheck(String password, String username) {
		System.out.println("=======passwordCheck 진입==========");
		
		return memberService.passwordCheck(password, username);
	}

}
