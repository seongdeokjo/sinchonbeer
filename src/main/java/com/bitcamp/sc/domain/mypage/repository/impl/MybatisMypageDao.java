package com.bitcamp.sc.domain.mypage.repository.impl;

import java.util.List;

import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.mypage.repository.MypageDao;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bitcamp.sc.domain.mypage.domain.OrderList;
import com.bitcamp.sc.domain.mypage.domain.RezList;
import com.bitcamp.sc.web.mypage.dto.EditMemberRequest;

@Repository
@RequiredArgsConstructor
public class MybatisMypageDao implements MypageDao {
	private static final String namespace = "MypageMapper";
	private final SqlSessionTemplate template;

	// 주문 내역 조회
	public List<OrderList> getOrderList(long idx) {
		return template.selectList(namespace + ".getOrderList", idx);
	}

	// 예약 내역 조회
	public List<RezList> getRezList(long idx) {
		return template.selectList(namespace + ".getRezList", idx);
	}


	@Override
	public Member findMemberAddressByMidx(long idx) {
		return template.selectOne(namespace+".findMemberAddressByMidx",idx);
	}

	// 회원 정보 수정
	public int updateMember(Member member){
		return template.update(namespace+".updateMember",member);
	}
}