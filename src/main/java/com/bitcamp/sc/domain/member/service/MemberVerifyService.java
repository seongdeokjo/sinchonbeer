package com.bitcamp.sc.domain.member.service;

import com.bitcamp.sc.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.member.repository.MemberDao;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberVerifyService {
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