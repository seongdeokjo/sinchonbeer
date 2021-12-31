package com.bitcamp.sc.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.member.service.MemberService;
import com.bitcamp.sc.order.domain.OrderInfo;
import com.bitcamp.sc.order.repository.OrderDao;
import com.bitcamp.sc.order.service.OrderService;

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
	public int createOrder(String type, OrderInfo orderInfo) {
		validateOrderInfo(type, orderInfo);

		orderInfo = orderDao.save(orderInfo);

		return orderInfo.getIdx();
	}

	@Override
	public OrderInfo getOrderInfo(int orderIdx) {
		OrderInfo orderInfo = orderDao.findByIdx(orderIdx);
		return orderInfo;
	}

	@Override
	public List<OrderInfo> getOrderInfos(int memberIdx) {
		List<OrderInfo> orderInfos = orderDao.findByMemberIdx(memberIdx);

		return orderInfos;
	}

	@Override
	public List<OrderInfo> getOrderInfosByType(String type, int memberIdx) {
		List<OrderInfo> orderInfos = orderDao.findByCategoryAndMemberIdx(type, memberIdx);

		return orderInfos;
	}
	
	@Override
	public int deleteOrder(int idx) {
		return orderDao.deleteByIdx(idx);
	}
	
	@Override
	public int changeOrderStatus(int idx, String status) {
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
	public int confirmOrder(int idx) {
		return orderDao.updateStatus("confirmed", idx);
	}

}
