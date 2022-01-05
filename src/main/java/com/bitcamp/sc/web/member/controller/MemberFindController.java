package com.bitcamp.sc.web.member.controller;

import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bitcamp.sc.domain.member.service.MemberFindService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members/find")
public class MemberFindController {
	// 아이디 찾기 서비스
	private final MemberFindService findService;

	// 이메일과 비밀번호 찾기 첫번째 화면
	@GetMapping
	public String getFindForm() {
		return "member/findEmailPwForm";
	}

	//(1) 아이디 찾기  - ajax에서 아이디 찾기 (요청)매핑
	// 현재 이름,전화번호가 중복이 생길 경우 에러 발생
	@PostMapping("/email")
	@ResponseBody
	public String getEmail(@RequestBody Map<String, Object> params) {
		return findService.getEmail((String) params.get("name"), (String) params.get("phone"));
	}

	//(2) 비밀번호 찾기  - ajax에서 비밀번호 찾기 (요청) 매핑
	@PostMapping("/pw")
	@ResponseBody
	public String getEmailForPw(@RequestBody Map<String, Object> params) {
		return findService.getEmailForPw((String) params.get("name"), (String) params.get("email"));
	}

	//비밀번호 재설정 - ajax로 처리
	@RequestMapping(value="/resetPw")
	@ResponseBody
	public String resetPw(@RequestBody Map<String, Object> params) {
		String resultStr = "N";
		Boolean result= findService.modifyPw((String)params.get("userEmail"), (String)params.get("userPw"));
		if(result) {
			resultStr="Y";
		}
		return resultStr;
	}
}