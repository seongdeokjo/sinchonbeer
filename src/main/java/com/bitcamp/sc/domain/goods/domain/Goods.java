package com.bitcamp.sc.domain.goods.domain;

import lombok.*;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Goods {


	private long idx; // 상품번호
	private String name; // 상품이름
	private int price; //상품가격
	private String photo; //상품이미지
	private String title; // 상품 상세 설명

	@Builder
	public Goods(String name, int price, String photo, String title) {
		this.name = name;
		this.price = price;
		this.photo = photo;
		this.title = title;
	}
}
