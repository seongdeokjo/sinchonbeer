package com.bitcamp.sc.domain.order.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bitcamp.sc.domain.order.domain.OrderInfo;
import com.bitcamp.sc.domain.order.repository.OrderDao;

public class MemoryOrderDao implements OrderDao {

	private static Map<Long, OrderInfo> store = new HashMap<>();
	private static int index;

	@Override
	public OrderInfo save(OrderInfo orderInfo) {
		orderInfo.setIdx(index++);
		store.put(orderInfo.getIdx(), orderInfo);
		return orderInfo;
	}

	@Override
	public OrderInfo findByIdx(long idx) {
		return store.get(idx);
	}

	@Override
	public List<OrderInfo> findByMemberIdx(long memberIdx) {
		List<OrderInfo> orderInfos = new ArrayList<>();
		for (long key : store.keySet()) {
			if (store.get(key).getMemberIdx() == memberIdx) {
				orderInfos.add(store.get(key));
			}
		}
		return orderInfos;
	}

	@Override
	public List<OrderInfo> findByCategoryAndMemberIdx(String category, long memberIdx) {
		List<OrderInfo> orderInfos = new ArrayList<>();
		for (long key : store.keySet()) {
			if (store.get(key).getCategory().equals(category) && store.get(key).getMemberIdx() == memberIdx) {
				orderInfos.add(store.get(key));
			}
		}
		return orderInfos;
	}

	public void clearStore() {
		store.clear();
		index = 0;
	}

	@Override
	public int deleteByIdx(long idx) {
		return 0;
	}

	@Override
	public int updateStatus(String status, long idx) {
		return 0;
	}
}
