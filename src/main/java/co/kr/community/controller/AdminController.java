package co.kr.community.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	// 회원 관리 페이지
	@GetMapping("/member")
	public ModelAndView memberListPage(@PageableDefault Pageable pageable) {
		ModelAndView mv = new ModelAndView("admin/memberList");
		Page<Member> members = memberService.getMemberList(pageable);
		mv.addObject("members", members);
		return mv;
	}
	
	@DeleteMapping("/member")
	@ResponseBody
	public String delete(@RequestParam(value="arr[]") List<String> arr) {
		System.out.println(arr);
		
		return "success";
	}
	

}
