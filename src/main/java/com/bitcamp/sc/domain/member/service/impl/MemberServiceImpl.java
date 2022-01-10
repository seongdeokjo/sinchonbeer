package com.bitcamp.sc.domain.member.service.impl;

import com.bitcamp.sc.domain.address.repository.AddressDao;
import com.bitcamp.sc.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.web.login.dto.LoginInfo;
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


}