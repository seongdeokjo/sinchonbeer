package com.bitcamp.sc.web.tour.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
public class TourDto {
	// 클라이언트 요청에 맵핑될 커맨드 클래스 db 저장 목적 x

	private long midx;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private String selectDate;
	private int tourPeople;
	private int price;
	private String category;
}