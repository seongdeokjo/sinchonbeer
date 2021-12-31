package com.bitcamp.sc.tour.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TourDto {
	// 클라이언트 요청에 맵핑될 커맨드 클래스 db 저장 목적 x

	private int midx;
	private String selectDate;
	private int tourPeople;
	private int price;
	private String category;
}