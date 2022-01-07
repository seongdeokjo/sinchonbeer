package com.bitcamp.sc.domain.pay.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PayInfo {

	private long idx;
	private int price;
	private Timestamp date;
	private String way;
	private String status;
	private long orderIdx;

	public PayInfo(int price, Timestamp date, String way, String status, long orderIdx) {
		this.price = price;
		this.way = way;
		this.status = status;
		this.orderIdx = orderIdx;
	}

}
