package co.kr.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.kr.community.entity.Member;
import co.kr.community.service.MemberService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	MemberService memberService;

	// 회원 관리 페이지 (페이징)
//	@GetMapping("/member")
//	public ModelAndView memberListPage(@PageableDefault Pageable pageable) {
//		ModelAndView mv = new ModelAndView("admin/memberList");
//		Page<Member> members = memberService.getMemberList(pageable);
//		mv.addObject("members", members);
//		return mv;
//	}
	
	
	// 회원 관리 페이지 (전체)
	@GetMapping("/member")
	public ModelAndView memberListPage() {
		ModelAndView mv = new ModelAndView("admin/memberList");
		List<Member> members = memberService.getMemberList();
		mv.addObject("members", members);
		return mv;
	}
	
	// 회원 관리 페이지 (관리자만)
	@GetMapping("/member/onlyAdmin")
	public ModelAndView onlyAdminList() {
		ModelAndView mv = new ModelAndView("admin/onlyAdminList");
		List<Member> members = memberService.getOnlyAdminList();
		mv.addObject("members", members);
		return mv;
	}
	
	
	// 회원 관리 페이지 (일반회원만)
	@GetMapping("/member/onlyMember")
	public ModelAndView onlyMemberList() {
		ModelAndView mv = new ModelAndView("admin/onlyMemberList");
		List<Member> members = memberService.getOnlyMemberList();
		System.out.println("onlymembers = =======>" + members.size());
		mv.addObject("members", members);
		return mv;
	}
	
	
	
	
	
	// 회원 삭제
	@DeleteMapping("/member")
	@ResponseBody
	public String unregister(@RequestParam(value="arr[]") List<String> arr) {
		
		for(int i=0; i<arr.size(); i++) {
			String username = arr.get(i);
			memberService.unregister(username);	
		}
		
		return "success";
	}
	
	// 계정 복구 (체크 박스 이용할 경우)
//	@PutMapping("/member")
//	@ResponseBody
//	public String accountRecovery(@RequestParam(value="arr[]") List<String> arr) {
//		
//		for(int i=0; i<arr.size(); i++) {
//			String username = arr.get(i);
//			memberService.accountRecovery(username);	
//		}
//		
//		return "success";
//	}
	
	// 계정 복구
	@PutMapping("/member")
	@ResponseBody
	public String accountRecovery(@RequestParam String username) {
				
		memberService.accountRecovery(username);	
		
		return "success";
	}
	
	// 권한 변경
	@PatchMapping("/member")
	@ResponseBody
	public String modifyAuthority(@RequestParam(value="arr[]") List<String> arr) {
		
		for(int i=0; i<arr.size(); i++) {
			String username = arr.get(i);
			memberService.modifyAuthority(username);
		}
		
		return "success";
	}

}
