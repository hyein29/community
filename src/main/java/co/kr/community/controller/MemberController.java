package co.kr.community.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.kr.community.entity.Member;
import co.kr.community.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	@Autowired
	JavaMailSender javaMailSender;
	
	// 로그인 페이지
	@GetMapping("/login")
	public String loginPage() {
		return "member/login";
	}
	
	// 회원 가입 페이지
	@GetMapping("/register")
	public String registerPage() {
		return "member/register";
	}
		
	// 회원 가입 처리
	@PostMapping("/register")
	public String register(Member member) {
		memberService.insert(member);
		return "redirect:/";
	}
	
	// 마이 페이지
	@GetMapping("/mypage")
	public ModelAndView mypage(Principal principal) {
		String username = principal.getName();
		ModelAndView mv = new ModelAndView("member/mypage");
		Optional<Member> myInfo = memberService.getMyInfo(username);
		mv.addObject("myInfo", myInfo.get());
		return mv;
	}
	
	// 회원 정보 수정 페이지
	@GetMapping("/mypage/modify")
	public ModelAndView modifyPage(Principal principal) {
		String username = principal.getName();
		ModelAndView mv = new ModelAndView("member/modify");
		Optional<Member> myInfo = memberService.getMyInfo(username);
		mv.addObject("myInfo", myInfo.get());
		return mv;
	}
	
	// 회원 정보 수정
	@PutMapping("/mypage/modify")
	public String modify(Member member) {
		memberService.update(member);
		return "redirect:/member/mypage/modify";
	}
	
	// 회원 탈퇴
	@DeleteMapping("/mypage")
	public String unregister(Principal principal) {
		String username = principal.getName();
		memberService.unregister(username);
		SecurityContextHolder.clearContext(); // 로그아웃 처리
		return "redirect:/";
	}

}
