package com.bitcamp.sc.web.mypage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyPageHomeController {

	// 주문 내역 페이지 이동
	@GetMapping("/mypage/home")
	public String mypageShop() {
			return "mypage/myPageHome";
	}
}