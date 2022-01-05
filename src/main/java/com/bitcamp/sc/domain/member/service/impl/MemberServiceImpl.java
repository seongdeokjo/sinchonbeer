package com.bitcamp.sc.domain.member.service.impl;

import com.bitcamp.sc.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.web.login.dto.LoginInfo;
import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.member.repository.MemberDao;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final SqlSessionTemplate template;
	
	// 회원의 midx로 회원 정보 조회하기.
	@Override
	public LoginInfo getMember(int idx) {
		return template.getMapper(MemberDao.class).findByMidx(idx).toLoginInfo();
	}

	// 회원의 midx로 주소 정보 가지고 오기
	@Override
	public Address getMemberAdd(int midx) {
		return template.getMapper(MemberDao.class).findAddressByMidx(midx);
	}

	//회원 수정 에서 기존 비밀번호 확인하기
	@Override
	public String getPw(int midx) {
		return template.getMapper(MemberDao.class).selectPw(midx);
	}
}
