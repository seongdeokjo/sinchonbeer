package com.bitcamp.sc.web.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitcamp.sc.domain.member.service.EmailCheckService;

@Controller
public class EmailCheckController {

	@Autowired
	private EmailCheckService chkService;
	
	//이메일 중복체크 - findEmailPwForm.html 에서 ajax로 처리
	@RequestMapping(value="/emailCheck", method=RequestMethod.POST)
	@ResponseBody
	public String idcheck(
			@RequestBody String email
			) {
		return chkService.emailCheck(email.replace("%40", "@")); 
	}
}
