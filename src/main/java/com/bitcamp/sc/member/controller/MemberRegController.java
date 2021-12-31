package com.bitcamp.sc.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.bitcamp.sc.member.domain.RegRequest;
import com.bitcamp.sc.member.service.MemberRegService;

@Controller
public class MemberRegController {
	
	@Autowired
	private MemberRegService regService;
	//일반회원 or 카카오 회원가입 선택하기
	@RequestMapping(value="/join/type",method=RequestMethod.GET)
	public String regMain() {
		return "member/regMain";
	}
	//회원가입 약관
	@RequestMapping(value="/join/agree", method=RequestMethod.GET)
	public String regAgree() {
		return "member/regAgree";
	}
	//회원가입 폼
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String regForm() {
		System.out.println("get 방식 진입 성공");
		return "member/regForm";
	}
	
	//회원가입 폼 - ajax로 처리(회원가입 여부 결과)
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public RedirectView reg(
			RegRequest regRequest
			) {
		regService.regMember(regRequest);
		return new RedirectView("/join/success");
	}
	
	//회원가입 성공
	@RequestMapping(value="/join/success", method=RequestMethod.GET)
	public String regSuccess() {
		return "member/regSuccess";
	}
	
}
