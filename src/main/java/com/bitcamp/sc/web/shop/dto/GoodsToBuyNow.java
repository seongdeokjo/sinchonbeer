package com.bitcamp.sc.web.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GoodsToBuyNow {

	private long idx;	// 상품번호
	private long orderIdx; // 주문번호
	private int amount;	//수량
	
}
