package com.bitcamp.sc.order.repository.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bitcamp.sc.order.domain.OrderInfo;
import com.bitcamp.sc.order.repository.OrderDao;

public class JdbcTemplateOrderDao implements OrderDao {

	private final JdbcTemplate template;

	@Autowired
	public JdbcTemplateOrderDao(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public OrderInfo save(OrderInfo orderInfo) {
		return null;
	}

	@Override
	public OrderInfo findByIdx(int idx) {
		List<OrderInfo> result = template.query("select * from orders where oidx = ?", orderRowMapper(), idx);
		return result.get(0);
	}

	@Override
	public List<OrderInfo> findByMemberIdx(int memberIdx) {
		return null;
	}

	@Override
	public List<OrderInfo> findByCategoryAndMemberIdx(String category, int memberIdx) {
		return null;
	}

	private RowMapper<OrderInfo> orderRowMapper() {
		return (rs, rowNum) -> {
			OrderInfo orderInfo = OrderInfo.builder()// category, price, tourIdx, tourPeople, memberIdx, addressIdx
					.category(rs.getString("ocategory")).price(rs.getInt("oprice")).tourIdx(rs.getInt("tidx"))
					.tourPeople(rs.getInt("tpeople")).memberIdx(rs.getInt("midx")).addressIdx(rs.getInt("aidx"))
					.build();
			return orderInfo;
		};
	}

	@Override
	public int deleteByIdx(int idx) {
		return 0;
	}

	@Override
	public int updateStatus(String status, int idx) {
		return 0;
	}
}
