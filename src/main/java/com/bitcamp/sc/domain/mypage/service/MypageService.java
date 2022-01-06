package com.bitcamp.sc.domain.mypage.service;

import java.util.List;

import com.bitcamp.sc.domain.mypage.domain.OrderList;
import com.bitcamp.sc.domain.mypage.domain.RezList;
import com.bitcamp.sc.domain.mypage.domain.UpdateMember;

public interface MypageService {

	// 주문 내역 조회
	List<OrderList> getOrderList(int idx);

	// 예약 내역 조회
	List<RezList> getRezList(int idx);

	// 회원 정보 조회
	List<UpdateMember> getMemberInfo(int idx);

	// 회원 정보 수정
	int updateMember(UpdateMember member);

	// 회원 탈퇴
	 void deleteMember(int idx);
}
