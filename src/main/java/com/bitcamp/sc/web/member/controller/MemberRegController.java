package com.bitcamp.sc.web.member.controller;

import com.bitcamp.sc.domain.member.service.MemberRegService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitcamp.sc.web.member.dto.RegRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRegController {
	
	private final MemberRegService regService;
	//일반회원 or 카카오 회원가입 선택하기
	@GetMapping("/add-type")
	public String regMain() {
		return "member/add/regMain";
	}
	//회원가입 약관
	@GetMapping("/add-agree")
	public String regAgree() {
		return "member/add/regAgree";
	}
	//회원가입 폼
	@GetMapping("/add")
	public String regForm(@ModelAttribute(name = "regRequest") RegRequest regRequest) {
		return "member/add/regForm";
	}

	@PostMapping("/add")
	public String reg(RegRequest regRequest) {
		log.info("regRequest ={}",regRequest.toString());
		if(regService.saveMember(regRequest)){
			return "redirect:/members/add-success";
		}
		return "member/add/regForm";
	}
	//회원가입 성공
	@GetMapping("/add-success")
	public String regSuccess() {
		return "member/add/regSuccess";
	}
}