package com.bitcamp.sc.web.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitcamp.sc.domain.member.service.MailSenderService;

@Controller
public class MailSenderController {

	@Autowired
	private MailSenderService service;
	
	
	//인증번호 메일로 전송하기 - ajax로 처리 (인증번호를 메일로 보내는 작업)
	@RequestMapping(value="/inquiry/pw/auth", method = RequestMethod.POST)
	@ResponseBody
	public String mailSend(@RequestBody Map<String, Object> param) {
		String result = "N";
		Boolean resultSaveCode = service.emailSender((String)param.get("userEmail"));
		if(resultSaveCode) {
			result="Y";
		}
		
		return result;
	}
}
