package co.kr.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.kr.community.entity.Member;
import co.kr.community.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	// 로그인 페이지
	@GetMapping("/login")
	public String loginPage() {
		return "member/login";
	}
	
	// 회원가입 페이지
	@GetMapping("/register")
	public String registerPage() {
		return "member/register";
	}
		
	// 회원가입 처리
	@PostMapping("/register")
	public String register(Member member) {
		memberService.insert(member);
		return "redirect:/";
	}
	

}
