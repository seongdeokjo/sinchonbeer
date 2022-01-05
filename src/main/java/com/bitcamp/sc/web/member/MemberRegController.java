package com.bitcamp.sc.web.member;

import com.bitcamp.sc.domain.member.domain.MemberAddressRequest;
import com.bitcamp.sc.domain.member.service.MemberRegService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String regForm(@ModelAttribute(name = "regRequest") RegRequest regRequest) {
		return "member/regFormV2";
	}

	@PostMapping("/add")
	public String reg(RegRequest regRequest) {
		log.info("regRequest ={}",regRequest.toString());
		regService.regMember(regRequest);
		return "redirect:/members/add-success";
	}
	//회원가입 성공
	@GetMapping("/add-success")
	public String regSuccess() {
		return "member/regSuccess";
	}
}
