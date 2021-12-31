//package com.bitcamp.sc.order.repository.impl;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.bitcamp.sc.order.domain.OrderInfo;
//
//@SpringBootTest
//class MybatisOrderDaoTest {
//
//	@Autowired
//	MybatisOrderDao dao;
//
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 주문등록() {
//		// given
//		OrderInfo order = new OrderInfo("shop", 11000, 1, 3, 2, 1); // category, price, tourIdx, tourPeople, memberIdx,
//																	// addressIdx
//
//		// when
//		dao.save(order);
//
//		// then
//		int idx = order.getIdx();
//
//		assertThat(dao.findByIdx(idx).getCategory()).isEqualTo(order.getCategory());
//		assertThat(dao.findByIdx(idx).getPrice()).isEqualTo(order.getPrice());
//		assertThat(dao.findByIdx(idx).getTourIdx()).isEqualTo(order.getTourIdx());
//		assertThat(dao.findByIdx(idx).getTourPeople()).isEqualTo(order.getTourPeople());
//		assertThat(dao.findByIdx(idx).getMemberIdx()).isEqualTo(order.getMemberIdx());
//		assertThat(dao.findByIdx(idx).getAddressIdx()).isEqualTo(order.getAddressIdx());
//	}
//
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 주문조회() {
//		// given
//		OrderInfo order = new OrderInfo("shop", 24000, 1, 3, 2, 1); // category, price, tourIdx, tourPeople, memberIdx,
//																	// addressIdx
//		dao.save(order);
//
//		// when
//		OrderInfo findOrder;
//		findOrder = dao.findByIdx(order.getIdx());
//
//		// then
//		assertThat(findOrder.getCategory()).isEqualTo(order.getCategory());
//		assertThat(findOrder.getPrice()).isEqualTo(order.getPrice());
//	}
//
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 주문조회_실패() {
//		// given
//		OrderInfo findOrder;
//
//		// when
//		findOrder = dao.findByIdx(-1);
//
//		// then
//		assertThrows(NullPointerException.class, () -> {
//			findOrder.getCategory();
//		});
//	}
//
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 멤버인덱스로_주문조회() {
//		// given
//
//		// 멤버 테이블에 의존하고 있기 때문에 테스트할 때마다 달라질 우려가 있으므로 향후에 테스트 방식을 바꿔야 함
//		OrderInfo order1 = new OrderInfo("shop", 22000, 1, 5, 3, 1); // category, price, tourIdx, tourPeople, memberIdx,
//																		// addressIdx
//		OrderInfo order2 = new OrderInfo("shop", 22000, 1, 3, 3, 1); // category, price, tourIdx, tourPeople, memberIdx,
//																		// addressIdx
//		dao.save(order1);
//		dao.save(order2);
//
//		// when
//		List<OrderInfo> findOrders = new ArrayList<>();
//		findOrders = dao.findByMemberIdx(3);
//
//		// then
//		assertThat(findOrders.size()).isEqualTo(3);
//	}
//
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 카테고리와_멤버인덱스로_주문조회() {
//		// given
//
//		// 멤버 테이블에 의존하고 있기 때문에 테스트할 때마다 달라질 우려가 있으므로 향후에 테스트 방식을 바꿔야 함
//		OrderInfo order1 = new OrderInfo("shop", 22000, 1, 5, 3, 1); // category, price, tourIdx, tourPeople, memberIdx,
//																		// addressIdx
//		OrderInfo order2 = new OrderInfo("tour", 22000, 1, 3, 3, 1); // category, price, tourIdx, tourPeople, memberIdx,
//																		// addressIdx
//		dao.save(order1);
//		dao.save(order2);
//
//		// when
//		List<OrderInfo> findOrders = new ArrayList<>();
//		findOrders = dao.findByCategoryAndMemberIdx("shop", 3);
//
//		// then
//		assertThat(findOrders.size()).isEqualTo(1);
//	}
//
//}
