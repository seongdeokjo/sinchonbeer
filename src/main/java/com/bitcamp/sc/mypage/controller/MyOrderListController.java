package com.bitcamp.sc.mypage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bitcamp.sc.member.domain.LoginInfo;
import com.bitcamp.sc.mypage.domain.OrderList;
import com.bitcamp.sc.mypage.service.MypageService;

@Controller
public class MyOrderListController {
	@Autowired
	MypageService service;

	// 주문 내역 페이지 이동
	@GetMapping("/mypage/orderList")
	public String mypageShop(Model model, HttpSession session) {
		LoginInfo login = (LoginInfo) session.getAttribute("loginInfo");

		if (session.getAttribute("loginInfo") == null) {
			return "member/loginForm";
		} else {
			List<OrderList> list = service.getOrderList(login.getIdx());
			model.addAttribute("list", list);
			
			System.out.println(list);

			return "mypage/orderList";
		}
	}
}
