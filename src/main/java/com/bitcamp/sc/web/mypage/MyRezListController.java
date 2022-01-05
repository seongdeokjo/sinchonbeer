package com.bitcamp.sc.web.mypage;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.bitcamp.sc.domain.mypage.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bitcamp.sc.web.login.dto.LoginInfo;
import com.bitcamp.sc.domain.mypage.domain.RezList;

@Controller
public class MyRezListController {
	@Autowired
    MypageService service;

	// 예약 내역 페이지 이동
	@GetMapping("/mypage/rezList")
	public String mypageTour(Model model, HttpSession session) {
		LoginInfo login = (LoginInfo) session.getAttribute("loginInfo");

		if (session.getAttribute("loginInfo") == null) {
			return "member/loginForm";
		} else {
			List<RezList> list = service.getRezList(login.getIdx());
			model.addAttribute("list", list);

			return "mypage/rezList";
		}
	}
}
