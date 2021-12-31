package com.bitcamp.sc.mypage.service;

import java.util.List;

import com.bitcamp.sc.mypage.domain.OrderList;
import com.bitcamp.sc.mypage.domain.RezList;
import com.bitcamp.sc.mypage.domain.UpdateMember;

public interface MypageService {

	// 주문 내역 조회
	List<OrderList> getOrderList(int idx);

	// 예약 내역 조회
	List<RezList> getRezList(int idx);

	// 회원 정보 조회
	List<UpdateMember> getMemberInfo(int idx);

	// 회원 정보 수정
	public int updateMember(UpdateMember member);

	// 회원 탈퇴
	public void deleteMember(int idx);
}
