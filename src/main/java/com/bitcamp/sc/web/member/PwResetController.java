package com.bitcamp.sc.web.member;

import java.util.Map;

import com.bitcamp.sc.domain.member.service.PwResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
