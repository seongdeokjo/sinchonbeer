package com.bitcamp.sc.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bitcamp.sc.member.domain.LoginInfo;
import com.bitcamp.sc.member.service.LoginService;


@Slf4j
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	//로그인 처리 get방식
	@GetMapping
	public String loginForm(
			//로그인 성공시 되돌아 갈 이전 페이지
			@RequestHeader(value = "referer", required = false) String redirectUri,
			@CookieValue(value = "reEmail", required = false) String reEmail,
			HttpSession session,
			Model model
	) {

		String view = "member/loginForm";
		LoginInfo login = (LoginInfo) session.getAttribute("loginInfo");
		if (login != null) {
			view = "main";
		}
		model.addAttribute("redirectUri", redirectUri);
		model.addAttribute("reEmail", reEmail);
		return view;
	}
	
	//로그인 처리 POST 방식 - ajax로 처리 (redirectUri와  로그인 결과를 data로 하여 전송하기)
	@PostMapping
	@ResponseBody
	public Map<String,Object> login(
			@RequestBody Map<String,Object> params,
			HttpSession session, //로그인 정보에 대한 session
			HttpServletResponse response //쿠키를 받기위한 response
			) {
		Map<String,Object> map = new HashMap<>();
		map.put("refererUri", (String)params.get("redirectUri"));
		
		//사용자가 입력한 정보를 서비스에서 처리하고 결과 받아오기
		Boolean loginChk = loginService.login(
				(String)params.get("memail"), 
				(String)params.get("mpw"), 
				(String)params.get("reEmail"), 
				session, 
				response
				);
		map.put("result", loginChk);
		
		return map;
	}
}