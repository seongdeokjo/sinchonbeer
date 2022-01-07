package com.bitcamp.sc.domain.order.repository;

import java.util.List;

import com.bitcamp.sc.domain.order.domain.OrderInfo;

public interface OrderDao {
	
	 OrderInfo save(OrderInfo orderInfo);
	 OrderInfo findByIdx(long idx);
	 List<OrderInfo> findByMemberIdx(long memberIdx);
	 List<OrderInfo> findByCategoryAndMemberIdx(String category, long memberIdx);
	 int deleteByIdx(long idx);
	 int updateStatus(String status, long idx);
	
}
