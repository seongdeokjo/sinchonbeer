package com.bitcamp.sc.domain.order.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bitcamp.sc.domain.order.domain.Order;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitcamp.sc.domain.order.domain.OrderInfo;
import com.bitcamp.sc.domain.order.repository.OrderDao;

@Repository
public class MybatisOrderDao implements OrderDao {

	private static final String NAME_SPACE = "OrderMapper";

	private final SqlSessionTemplate template;

	@Autowired
	public MybatisOrderDao(SqlSessionTemplate template) {
		this.template = template;
	}

	@Override
	public OrderInfo save(OrderInfo orderInfo) {
		template.insert(NAME_SPACE + ".save", orderInfo);
		return orderInfo;
	}

	@Override
	public OrderInfo findByIdx(long idx) {
		return template.selectOne(NAME_SPACE + ".findByIdx", idx);
	}

	@Override
	public List<OrderInfo> findByMemberIdx(long memberIdx) {
		return template.selectList(NAME_SPACE + ".findByMemberIdx", memberIdx);
	}

	@Override
	public List<OrderInfo> findByCategoryAndMemberIdx(String category, long memberIdx) {
		Map<String, Object> param = new HashMap<>();
		param.put("category", category);
		param.put("memberIdx", memberIdx);
		return template.selectList(NAME_SPACE + ".findByCategoryAndMemberIdx", param);
	}

	@Override
	public int deleteByIdx(long idx) {
		return template.delete(NAME_SPACE + ".deleteByIdx", idx);
	}

	@Override
	public int updateStatus(String status, long idx) {
		Map<String, Object> param = new HashMap<>();
		param.put("status", status);
		param.put("idx", idx);
		return template.update(NAME_SPACE + ".updateStatus", param);
	}

	@Override
	public List<Order> findTourOrderByMidxAndCategory(long midx, String category) {
		Map<String, Object> param = new HashMap<>();
		param.put("midx", midx);
		param.put("category", category);
		return template.selectList(NAME_SPACE+".findOrderTourByMidx",param);
	}

}
