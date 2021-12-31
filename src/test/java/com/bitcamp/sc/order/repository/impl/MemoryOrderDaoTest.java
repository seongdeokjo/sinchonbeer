package com.bitcamp.sc.order.repository.impl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bitcamp.sc.order.domain.OrderInfo;

class MemoryOrderDaoTest {

	MemoryOrderDao dao;

	@BeforeEach
	void beforeEach() {
		dao = new MemoryOrderDao();
	}

	@AfterEach
	void afterEach() {
		dao.clearStore();
	}

	@Test
	void 주문등록() {
		// given
		OrderInfo order = new OrderInfo("shop", 22000, 1, 3, 2, 1); // category, price, tourIdx, tourPeople, memberIdx,
																	// addressIdx

		// when
		dao.save(order);

		// then
		assertThat(order).isEqualTo(dao.findByIdx(order.getIdx()));
	}

	@Test
	void 주문조회() {
		// given
		OrderInfo order = new OrderInfo("shop", 22000, 1, 3, 2, 1); // category, price, tourIdx, tourPeople, memberIdx,
																	// addressIdx
		dao.save(order);

		// when
		OrderInfo findOrder = new OrderInfo();
		findOrder = dao.findByIdx(order.getIdx());

		// then
		assertThat(findOrder).isEqualTo(order);
	}

	@Test
	void 주문조회_실패() {
		// given
		OrderInfo order = new OrderInfo("shop", 22000, 1, 3, 2, 1); // category, price, tourIdx, tourPeople, memberIdx,
																	// addressIdx
		dao.save(order);

		// when
		OrderInfo findOrder = new OrderInfo();
		findOrder = dao.findByIdx(-1);

		// then
		assertThat(findOrder).isNotEqualTo(order);
	}

	@Test
	void 멤버인덱스로_주문조회() {
		// given
		OrderInfo order1 = new OrderInfo("shop", 22000, 1, 3, 2, 1); // category, price, tourIdx, tourPeople, memberIdx,
																		// addressIdx
		OrderInfo order2 = new OrderInfo("shop", 22000, 1, 3, 2, 1); // category, price, tourIdx, tourPeople, memberIdx,
																		// addressIdx
		dao.save(order1);
		dao.save(order2);

		// when
		List<OrderInfo> findOrders = new ArrayList<>();
		findOrders = dao.findByMemberIdx(2);

		// then
		assertThat(findOrders.get(0)).isEqualTo(order1);
		assertThat(findOrders.get(1)).isEqualTo(order2);
	}

}
