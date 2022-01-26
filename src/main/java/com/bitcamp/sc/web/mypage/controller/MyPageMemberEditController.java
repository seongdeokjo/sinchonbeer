package com.bitcamp.sc.web.mypage.controller;

import javax.servlet.http.HttpSession;

import com.bitcamp.sc.domain.member.service.MemberEditService;
import com.bitcamp.sc.web.SessionConst;
import com.bitcamp.sc.web.mypage.dto.EditMemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bitcamp.sc.web.login.dto.LoginInfo;
import com.bitcamp.sc.web.mypage.dto.EditMemberRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/member")
public class MyPageMemberEditController {

	private final MemberEditService service;
	private final PasswordEncoder passwordEncoder;

	// 정보 수정 페이지 이동(회원 정보 조회)
	@GetMapping("/{midx}/edit")
	public String editInfoGet(@PathVariable long midx, Model model) {
			EditMemberResponse list = service.getMemberInfo(midx);
			model.addAttribute("idx",midx);
			model.addAttribute("list", list);
			return "mypage/edit-info";
	}

	// 정보 수정 실행
	@PostMapping("/{midx}/edit")
	public String editInfoPost(@PathVariable long midx, EditMemberRequest member) {
		service.updateMember(midx,member);
		return "redirect:/mypage/member/{midx}/edit";
	}

	// 비밀번호 DB 일치 여부 확인
	@PostMapping("/pwCheckEdit")
	@ResponseBody
	public String pwCheckPost(@RequestBody String oldPw, HttpSession session) {
		LoginInfo login = (LoginInfo) session.getAttribute(SessionConst.LOGIN_MEMBER);
		if (passwordEncoder.matches(oldPw, service.getPw(login.getIdx()))) {
			return "Y";
		}
		return "N";
	}
}