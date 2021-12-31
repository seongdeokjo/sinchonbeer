package com.bitcamp.sc.order.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitcamp.sc.order.domain.OrderInfo;
import com.bitcamp.sc.order.repository.OrderDao;

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
	public OrderInfo findByIdx(int idx) {
		return template.selectOne(NAME_SPACE + ".findByIdx", idx);
	}

	@Override
	public List<OrderInfo> findByMemberIdx(int memberIdx) {
		return template.selectList(NAME_SPACE + ".findByMemberIdx", memberIdx);
	}

	@Override
	public List<OrderInfo> findByCategoryAndMemberIdx(String category, int memberIdx) {
		Map<String, Object> param = new HashMap<>();
		param.put("category", category);
		param.put("memberIdx", memberIdx);
		return template.selectList(NAME_SPACE + ".findByCategoryAndMemberIdx", param);
	}

	@Override
	public int deleteByIdx(int idx) {
		return template.delete(NAME_SPACE + ".deleteByIdx", idx);
	}

	@Override
	public int updateStatus(String status, int idx) {
		Map<String, Object> param = new HashMap<>();
		param.put("status", status);
		param.put("idx", idx);
		return template.update(NAME_SPACE + ".updateStatus", param);
	}

}
