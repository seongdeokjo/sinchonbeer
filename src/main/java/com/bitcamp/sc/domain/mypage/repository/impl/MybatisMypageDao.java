package com.bitcamp.sc.domain.mypage.repository.impl;

import java.util.List;

import com.bitcamp.sc.domain.mypage.repository.MypageDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bitcamp.sc.domain.mypage.domain.OrderList;
import com.bitcamp.sc.domain.mypage.domain.RezList;
import com.bitcamp.sc.domain.mypage.domain.UpdateMember;

@Repository
public class MybatisMypageDao implements MypageDao {
	private static final String namespace = "MypageMapper";
	private final SqlSessionTemplate template;

	public MybatisMypageDao(SqlSessionTemplate template) {
		this.template = template;
	}

	// 주문 내역 조회
	public List<OrderList> getOrderList(long idx) {
		return template.selectList(namespace + ".getOrderList", idx);
	}

	// 예약 내역 조회
	public List<RezList> getRezList(long idx) {
		return template.selectList(namespace + ".getRezList", idx);
	}

	// 회원 정보 조회 --> return Member로 변경
	public UpdateMember getMemberInfo(long idx){
		return template.selectOne(namespace+".getMemberInfo",idx);
	}

	// 회원 정보 수정
	public int updateMember(UpdateMember member){
		return template.update(namespace+".updateMember",member);
	}
}