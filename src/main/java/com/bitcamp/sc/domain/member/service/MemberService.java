package com.bitcamp.sc.domain.member.service;

import com.bitcamp.sc.web.login.dto.LoginInfo;
import com.bitcamp.sc.domain.address.domain.Address;

public interface MemberService {
	//회원의 midx로 회원 정보 조회하기.
	LoginInfo getMember(long midx);

}