package com.bitcamp.sc.mypage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitcamp.sc.member.domain.LoginInfo;
import com.bitcamp.sc.member.service.MemberService;
import com.bitcamp.sc.mypage.domain.UpdateMember;
import com.bitcamp.sc.mypage.service.MypageService;

@Controller
public class MemberEditController {
	MypageService service;
	MemberService memberService;
	PasswordEncoder passwordEncoder;

	@Autowired
	public MemberEditController(MypageService service, MemberService memberService, PasswordEncoder passwordEncoder) {
		this.service = service;
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}

	// 정보 수정 페이지 이동(회원 정보 조회)
	@GetMapping("/mypage/edit-info")
	public String editInfoGet(Model model, HttpSession session) {
		LoginInfo login = (LoginInfo) session.getAttribute("loginInfo");

		if (session.getAttribute("loginInfo") == null) {

			return "member/loginForm";
		} else {
			List<UpdateMember> list = service.getMemberInfo(login.getIdx());
			model.addAttribute("list", list);
			return "mypage/edit-info";
		}
	}

	// 정보 수정 실행
	@PostMapping("/mypage/edit-info")
	public String editInfoPost(UpdateMember member) {
		service.updateMember(member);
		return "redirect:/mypage/edit-info";
	}

	// 비밀번호 DB 일치 여부 확인
	@PostMapping("/pwCheckEdit")
	@ResponseBody
	public String pwCheckPost(@RequestBody String oldPw, HttpSession session) {
		LoginInfo login = (LoginInfo) session.getAttribute("loginInfo");

		if (passwordEncoder.matches(oldPw, memberService.getPw(login.getIdx()))) {
			return "Y";
		}
		return "N";
	}
}
