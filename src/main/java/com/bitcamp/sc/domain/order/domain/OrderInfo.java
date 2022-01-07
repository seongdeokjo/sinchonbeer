package com.bitcamp.sc.domain.order.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
	private long idx;
	private Timestamp date;
	private String category;
	private int price;
	private long tourIdx;
	private Integer tourPeople;
	private long memberIdx;
	private long addressIdx;
	private long goodsIdx;
	private Integer amount;

	public OrderInfo(String category, int price, long tourIdx, Integer tourPeople, long memberIdx, long addressIdx) {
		this.category = category;
		this.price = price;
		this.tourIdx = tourIdx;
		this.tourPeople = tourPeople;
		this.memberIdx = memberIdx;
		this.addressIdx = addressIdx;
	}
}
