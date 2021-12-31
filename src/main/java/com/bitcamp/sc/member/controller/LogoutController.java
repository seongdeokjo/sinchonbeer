package com.bitcamp.sc.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		//로그인 세션이 있다면 세션을 끊고 메인페이지로 보내주기
		return "redirect:main";
	}
}
