package com.bitcamp.sc.pay.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PayInfo {

	private int idx;
	private int price;
	private Timestamp date;
	private String way;
	private String status;
	private int orderIdx;

	public PayInfo(int price, Timestamp date, String way, String status, int orderIdx) {
		this.price = price;
		this.way = way;
		this.status = status;
		this.orderIdx = orderIdx;
	}

}
