package com.bitcamp.sc.domain.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GoodsVO {

	
	private int idx; // 상품번호
	private String gname; // 상품이름
	private int price; //상품가격
	private String photo; //상품이미지
	private String text; // 상품 상세 설명
	
	public GoodsVO(String gname, int price, String photo, String text) {
		this.gname = gname;
		this.price = price;
		this.photo = photo;
		this.text = text;
	}
}
