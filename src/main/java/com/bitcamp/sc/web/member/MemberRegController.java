package com.bitcamp.sc.web.member;

import com.bitcamp.sc.domain.member.service.MemberRegService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.bitcamp.sc.domain.member.domain.RegRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRegController {
	
	private final MemberRegService regService;
	//일반회원 or 카카오 회원가입 선택하기
	@GetMapping("/add-type")
	public String regMain() {
		return "member/regMain";
	}
	//회원가입 약관
	@GetMapping("/add-agree")
	public String regAgree() {
		return "member/regAgree";
	}
	//회원가입 폼
	@GetMapping("/add")
	public String regForm() {
		System.out.println("get 방식 진입 성공");
		return "member/regForm";
	}
	
	//회원가입 폼 - ajax로 처리(회원가입 여부 결과)
	@PostMapping("/add")
	public RedirectView reg(RegRequest regRequest) {
		regService.regMember(regRequest);
		return new RedirectView("/join/success");
	}
	//회원가입 성공
	@GetMapping("/add-success")
	public String regSuccess() {
		return "member/regSuccess";
	}
}
