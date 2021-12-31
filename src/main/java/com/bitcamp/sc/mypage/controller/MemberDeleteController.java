package com.bitcamp.sc.mypage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitcamp.sc.member.domain.LoginInfo;
import com.bitcamp.sc.member.service.MemberService;
import com.bitcamp.sc.mypage.service.MypageService;

@Controller
public class MemberDeleteController {
	MypageService service;
	MemberService memberService;
	PasswordEncoder passwordEncoder;

	@Autowired
	public MemberDeleteController(MypageService service, MemberService memberService, PasswordEncoder passwordEncoder) {
		this.service = service;
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}

	// 회원 탈퇴 페이지 이동
	@GetMapping("/mypage/delete-id")
	public String deleteIdGet(HttpSession session) {

		if (session.getAttribute("loginInfo") == null) {
			return "member/loginForm";
		}
		return "mypage/delete-id";
	}

	// 회원 탈퇴 실행
	@PostMapping("/mypage/delete-id")
	public String deleteIdPost(HttpSession session) {
		LoginInfo login = (LoginInfo) session.getAttribute("loginInfo");

		service.deleteMember(login.getIdx());

		// 세션 초기화
		session.invalidate();

		return "redirect:/main";
	}

	// 비밀번호 DB 일치 여부 확인
	@PostMapping("/pwCheckDelete")
	@ResponseBody
	public String pwCheckPost(@RequestBody String pw, HttpSession session) {
		LoginInfo login = (LoginInfo) session.getAttribute("loginInfo");
		System.out.println("pw:"+pw);
		if (passwordEncoder.matches(pw, memberService.getPw(login.getIdx()))) {
			return "Y";
		}
		return "N";
	}
}
