package com.bitcamp.sc.domain.member.service.impl;

import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import com.bitcamp.sc.domain.member.service.MemberVerifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberVerifyServiceImpl implements MemberVerifyService {
	private final MemberDao memberDao;

	//이메일이 중복이면  "Y", 중복되지 않았으면 "N"를 반환
	public String checkEmail(String email) {
		String result="Y";
		Member member = memberDao.findByEmail(email);
		if(member != null) {
			result="N";
		}
		return result;
	}

	public Boolean checkCode(String inputNum, String email) {

		Boolean result = false;
		String verifyCode = memberDao.findByEmail(email).getCode();
		if (verifyCode.equals(inputNum)) {
			result = true;
		}
		return result;
	}
}