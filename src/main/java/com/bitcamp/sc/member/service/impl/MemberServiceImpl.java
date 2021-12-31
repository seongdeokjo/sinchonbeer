package com.bitcamp.sc.member.service.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.member.domain.LoginInfo;
import com.bitcamp.sc.member.domain.MemberAddress;
import com.bitcamp.sc.member.repository.MemberDao;
import com.bitcamp.sc.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSessionTemplate template;
	
	// 회원의 midx로 회원 정보 조회하기.
	@Override
	public LoginInfo getMember(int midx) {
		return template.getMapper(MemberDao.class).selectByMidx(midx).toLoginInfo();
	}

	// 회원의 midx로 주소 정보 가지고 오기
	@Override
	public MemberAddress getMemberAdd(int midx) {
		return template.getMapper(MemberDao.class).selectAddressByMidx(midx);
	}
	//회원 수정 에서 기존 비밀번호 확인하기
	@Override
	public String getPw(int midx) {
		return template.getMapper(MemberDao.class).selectPw(midx);
	}
}
