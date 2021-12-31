package com.bitcamp.sc.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitcamp.sc.member.service.PwResetService;

@Controller
public class PwResetController {

	@Autowired
	private PwResetService service;
	
	//비밀번호 재설정 - ajax로 처리
	@RequestMapping(value="/inquiry/pw/reset")
	@ResponseBody
	public String resetPw(
			@RequestBody Map<String, Object> params
			) {
		String resultStr = "N";
		Boolean result= service.updateNewPw((String)params.get("userEmail"), (String)params.get("userPw"));
		
		if(result) {
			resultStr="Y";
		}
		
		return resultStr;
	}
	
}
