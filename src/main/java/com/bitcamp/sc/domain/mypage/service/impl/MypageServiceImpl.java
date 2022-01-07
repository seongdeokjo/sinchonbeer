package com.bitcamp.sc.domain.mypage.service.impl;

import java.util.List;

import com.bitcamp.sc.domain.member.repository.MemberDao;
import com.bitcamp.sc.domain.mypage.repository.MypageDao;
import com.bitcamp.sc.domain.mypage.service.MypageService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.mypage.domain.OrderList;
import com.bitcamp.sc.domain.mypage.domain.RezList;
import com.bitcamp.sc.domain.mypage.domain.UpdateMember;

@Service
public class MypageServiceImpl implements MypageService {
	MypageDao dao;
	MemberDao memberDao;
	SqlSessionTemplate template;
	PasswordEncoder passwordEncoder;

	@Autowired
	public MypageServiceImpl(MypageDao dao, SqlSessionTemplate template, PasswordEncoder passwordEncoder) {
		this.dao = dao;
		this.template = template;
		this.passwordEncoder = passwordEncoder;
	}

	// 주문 내역 조회
	@Override
	public List<OrderList> getOrderList(long idx) {
		return dao.getOrderList(idx);
	}

	// 예약 내역 조회
	@Override
	public List<RezList> getRezList(long idx) {
		return dao.getRezList(idx);
	}




}