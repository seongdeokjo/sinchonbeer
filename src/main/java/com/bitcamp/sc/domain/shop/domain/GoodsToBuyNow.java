package com.bitcamp.sc.domain.shop.domain;

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

	private int idx;	// 상품번호
	private int orderIdx; // 주문번호
	private int amount;	//수량
	
}
