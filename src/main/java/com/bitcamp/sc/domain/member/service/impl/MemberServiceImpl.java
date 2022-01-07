package com.bitcamp.sc.domain.member.service.impl;

import com.bitcamp.sc.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.web.login.dto.LoginInfo;
import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

	private final MemberDao memberDao;

	// 회원의 midx로 회원 정보 조회하기.
	@Override
	public LoginInfo getMember(long idx) {
		return memberDao.findByMidx(idx).toLoginInfo();
	}

	// 회원의 midx로 주소 정보 가지고 오기
	@Override
	public Address getMemberAdd(long midx) {
		return memberDao.findAddressByMidx(midx);
	}
}