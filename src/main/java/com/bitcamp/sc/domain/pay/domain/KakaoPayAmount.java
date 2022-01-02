package com.bitcamp.sc.domain.pay.domain;

import lombok.Data;

@Data
public class KakaoPayAmount {

	private int total;
	private int tax_free;
	private int vat;
	private int point;
	private int discount;
}
