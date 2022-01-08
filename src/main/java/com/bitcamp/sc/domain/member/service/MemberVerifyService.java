package com.bitcamp.sc.domain.member.service;

public interface MemberVerifyService {

	//이메일이 중복이면  "Y", 중복되지 않았으면 "N"를 반환
	 String checkEmail(String email);

	 Boolean checkCode(String inputNum, String email);
}