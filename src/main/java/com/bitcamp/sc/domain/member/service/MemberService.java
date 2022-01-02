package com.bitcamp.sc.domain.member.service;

import com.bitcamp.sc.domain.member.domain.LoginInfo;
import com.bitcamp.sc.domain.member.domain.MemberAddress;

public interface MemberService {
	
	//회원의 midx로 회원 정보 조회하기.
	LoginInfo getMember(int midx);
	
	//회원의 midx로 주소 정보 가지고 오기 
	MemberAddress getMemberAdd(int midx);
	
	//회원 수정 에서 기존 비밀번호 확인하기
	String getPw(int midx);
	
}
