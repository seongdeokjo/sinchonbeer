package com.bitcamp.sc.order.repository;

import java.util.List;

import com.bitcamp.sc.order.domain.OrderInfo;

public interface OrderDao {
	
	public OrderInfo save(OrderInfo orderInfo);
	public OrderInfo findByIdx(int idx);
	public List<OrderInfo> findByMemberIdx(int memberIdx);
	public List<OrderInfo> findByCategoryAndMemberIdx(String category, int memberIdx);
	public int deleteByIdx(int idx);
	public int updateStatus(String status, int idx);
	
}
