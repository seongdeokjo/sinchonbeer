package com.bitcamp.sc.web.member.controller;

import javax.servlet.http.HttpSession;

import com.bitcamp.sc.domain.member.service.MemberEditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitcamp.sc.web.login.dto.LoginInfo;
import com.bitcamp.sc.web.mypage.dto.EditMemberRequestDto;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberEditController {

	private final MemberEditService service;
	private final PasswordEncoder passwordEncoder;

	// 정보 수정 페이지 이동(회원 정보 조회)
	@GetMapping("/mypage/edit-info")
	public String editInfoGet(Model model, HttpSession session) {
		LoginInfo login = (LoginInfo) session.getAttribute("loginInfo");
			EditMemberRequestDto list = service.getMemberInfo(login.getIdx());
			model.addAttribute("list", list);
			return "mypage/edit-info";

	}

	// 정보 수정 실행
	@PostMapping("/mypage/edit-info")
	public String editInfoPost(EditMemberRequestDto member) {
		service.updateMember(member);
		return "redirect:/mypage/edit-info";
	}

	// 비밀번호 DB 일치 여부 확인
	@PostMapping("/pwCheckEdit")
	@ResponseBody
	public String pwCheckPost(@RequestBody String oldPw, HttpSession session) {
		LoginInfo login = (LoginInfo) session.getAttribute("loginInfo");
		if (passwordEncoder.matches(oldPw, service.getPw(login.getIdx()))) {
			return "Y";
		}
		return "N";
	}
}