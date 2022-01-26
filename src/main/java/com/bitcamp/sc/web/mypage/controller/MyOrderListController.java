package com.bitcamp.sc.web.mypage.controller;

import java.util.List;

import com.bitcamp.sc.domain.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bitcamp.sc.domain.mypage.domain.OrderList;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyOrderListController {
    private final MypageService service;

	// 주문 내역 페이지 이동
	@GetMapping("/mypage/orderList/{id}")
	public String mypageShop(Model model, @PathVariable("id") long id) {
			List<OrderList> list = service.getOrderList(id);
			model.addAttribute("idx",id);
			model.addAttribute("list", list);
			return "mypage/orderList";
	}
}