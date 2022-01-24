package com.bitcamp.sc.web.mypage.controller;

import com.bitcamp.sc.web.login.dto.LoginInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyPageHomeController {

	// 주문 내역 페이지 이동
	@GetMapping("/mypage/home")
	public String mypageShop(HttpSession session, Model model) {
		LoginInfo loginMember = (LoginInfo) session.getAttribute("loginMember");
		long idx = loginMember.getIdx();
		model.addAttribute("idx",idx);
		return "mypage/myPageHome";
	}
}