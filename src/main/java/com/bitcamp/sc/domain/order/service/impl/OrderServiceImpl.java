package com.bitcamp.sc.domain.order.service.impl;

import java.util.List;

import com.bitcamp.sc.domain.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.member.service.MemberService;
import com.bitcamp.sc.domain.order.domain.OrderInfo;
import com.bitcamp.sc.domain.order.repository.OrderDao;

@Service
public class OrderServiceImpl implements OrderService {

	OrderDao orderDao;
	MemberService memberService;

	@Autowired
	public OrderServiceImpl(OrderDao orderDao, MemberService memberService) {
		this.orderDao = orderDao;
		this.memberService = memberService;
	}

	@Override
	public long createOrder(String type, OrderInfo orderInfo) {
		validateOrderInfo(type, orderInfo);

		orderInfo = orderDao.save(orderInfo);

		return orderInfo.getIdx();
	}

	@Override
	public OrderInfo getOrderInfo(long orderIdx) {
		OrderInfo orderInfo = orderDao.findByIdx(orderIdx);
		return orderInfo;
	}

	@Override
	public List<OrderInfo> getOrderInfos(long memberIdx) {
		List<OrderInfo> orderInfos = orderDao.findByMemberIdx(memberIdx);

		return orderInfos;
	}

	@Override
	public List<OrderInfo> getOrderInfosByType(String type, long memberIdx) {
		List<OrderInfo> orderInfos = orderDao.findByCategoryAndMemberIdx(type, memberIdx);

		return orderInfos;
	}
	
	@Override
	public int deleteOrder(long idx) {
		return orderDao.deleteByIdx(idx);
	}
	
	@Override
	public int changeOrderStatus(long idx, String status) {
		return orderDao.updateStatus(status, idx);
	}

	private void validateOrderInfo(String type, OrderInfo orderInfo) {
		if (!type.isEmpty() && type.equals("tour")) {
			validateTourOrder(orderInfo);
		} else if (!type.isEmpty() && type.equals("shop")) {
			validateShopOrder(orderInfo);
		} else {
			throw new IllegalStateException("주문 정보가 누락됐습니다. 다시 입력해주세요.");
		}
	}

	private void validateTourOrder(OrderInfo orderInfo) {
		if (orderInfo.getCategory().equals("") || orderInfo.getPrice() == 0 || orderInfo.getMemberIdx() == 0
				|| orderInfo.getTourIdx() == 0 || orderInfo.getTourPeople() == 0) {
			throw new IllegalStateException("여행 주문 정보가 누락됐습니다. 다시 입력해주세요.");
		}
	}

	private void validateShopOrder(OrderInfo orderInfo) {
		if (orderInfo.getCategory().equals("") || orderInfo.getPrice() == 0 || orderInfo.getMemberIdx() == 0
				|| orderInfo.getAddressIdx() == 0) {
			throw new IllegalStateException("상품 주문 정보가 누락됐습니다. 다시 입력해주세요.");
		}
	}

	@Override
	public int confirmOrder(long idx) {
		return orderDao.updateStatus("confirmed", idx);
	}

}
