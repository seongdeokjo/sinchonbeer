package com.bitcamp.sc.mypage.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bitcamp.sc.mypage.domain.OrderList;
import com.bitcamp.sc.mypage.domain.RezList;
import com.bitcamp.sc.mypage.domain.UpdateMember;

@Repository
public class MypageDao {
	private static final String namespace = "MypageMapper";
	private final SqlSessionTemplate template;

	public MypageDao(SqlSessionTemplate template) {
		this.template = template;
	}

	// 주문 내역 조회
	public List<OrderList> getOrderList(int idx) {
		return template.selectList(namespace + ".getOrderList", idx);
	}

	// 예약 내역 조회
	public List<RezList> getRezList(int idx) {
		return template.selectList(namespace + ".getRezList", idx);
	}

	// 회원 정보 조회
	public List<UpdateMember> getMemberInfo(int idx) {
		return template.selectList(namespace + ".getMemberInfo", idx);
	}

	// 회원 정보 수정
	public int updateMember(UpdateMember member) {
		return template.update(namespace + ".updateMember", member);
	}

	// 회원 탈퇴
	public void deleteMember(int idx) {
		template.delete(namespace + ".deleteMember", idx);
	}
}
