package com.bitcamp.sc.domain.mypage.service;

import java.util.List;

import com.bitcamp.sc.domain.mypage.domain.OrderList;
import com.bitcamp.sc.domain.mypage.domain.RezList;

public interface MypageService {
	// 주문 내역 조회
	List<OrderList> getOrderList(long idx);

	// 예약 내역 조회
	List<RezList> getRezList(long idx);
}