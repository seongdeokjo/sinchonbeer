package com.bitcamp.sc.web.tour.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
public class TourDto {

	private long midx;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private String selectDate;
	private int tourPeople;
	private int price;
	private String category;
}