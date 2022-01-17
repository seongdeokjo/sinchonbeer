package com.bitcamp.sc.domain.order.service;

import java.util.List;

import com.bitcamp.sc.domain.order.domain.OrderInfo;

public interface OrderService {
	long createOrder(OrderInfo orderInfo); // 새로운 주문을 생성
	OrderInfo getOrderInfo(long orderIdx); // 주문 번호로 주문정보를 받아옴
	List<OrderInfo> getOrderInfos(long memberIdx); // 유저 아이디로 주문정보 리스트를 받아옴
	List<OrderInfo> getOrderInfosByType(String type, long memberIdx); // 주문 카테고리와 유저 아이디로 주문정보 리스트를 받아옴
	int deleteOrder(long idx);
	int confirmOrder(long idx);
	int changeOrderStatus(long idx, String status);
}
