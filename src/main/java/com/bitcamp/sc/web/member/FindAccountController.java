package com.bitcamp.sc.web.member;

import java.util.Map;

import com.bitcamp.sc.domain.member.service.PwFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitcamp.sc.domain.member.service.EmailFindService;

@Controller
public class FindAccountController {
	// 아이디 찾기 서비스
	@Autowired
	private EmailFindService emailFindService;


	// 비밀번호 찾기 서비스
	@Autowired
	private PwFindService pwFindService;

	
	// 이메일과 비밀번호 찾기 첫번째 화면
	@RequestMapping(value = "/inquiry", method = RequestMethod.GET)
	public String findAccount() {
		return "member/findEmailPwForm";
	}

	//(1) 아이디 찾기  - ajax에서 아이디 찾기 (요청)매핑
	@RequestMapping(value = "/inquiry/email", method = RequestMethod.POST)
	@ResponseBody
	public String findEmailByNamePhone(@RequestBody Map<String, Object> params) {
		return emailFindService.emailSearch((String) params.get("name"), (String) params.get("phone"));
	}
	
	//(2) 비밀번호 찾기  - ajax에서 비밀번호 찾기 (요청) 매핑
	@RequestMapping(value = "/inquiry/pw", method = RequestMethod.POST)
	@ResponseBody
	public String findEmailByNameEmail(@RequestBody Map<String, Object> params) {
		return pwFindService.pwSearch((String) params.get("name"), (String) params.get("email"));
	}

}


