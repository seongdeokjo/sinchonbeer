package com.bitcamp.sc.mypage.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.mypage.domain.OrderList;
import com.bitcamp.sc.mypage.domain.RezList;
import com.bitcamp.sc.mypage.domain.UpdateMember;
import com.bitcamp.sc.mypage.repository.MypageDao;
import com.bitcamp.sc.mypage.service.MypageService;

@Service
public class MypageServiceImpl implements MypageService {
	MypageDao dao;
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
	public List<OrderList> getOrderList(int idx) {
		return dao.getOrderList(idx);
	}

	// 예약 내역 조회
	@Override
	public List<RezList> getRezList(int idx) {
		return dao.getRezList(idx);
	}

	// 회원 정보 조회
	@Override
	public List<UpdateMember> getMemberInfo(int idx) {
		return dao.getMemberInfo(idx);
	}

	// 회원 정보 수정
	@Override
	public int updateMember(UpdateMember member) {
		String securityPw = passwordEncoder.encode(member.getMnewPw());
		member.setMnewPw(securityPw);
		
		return dao.updateMember(member);
	}

	// 회원 탈퇴
	@Override
	public void deleteMember(int idx) {
		dao.deleteMember(idx);
	}
}
