package com.bitcamp.sc.web.tour.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ChangeTourDto {
	private int oidx;
	private int tourPeople;
	private String resDate;
	private String newDate;
}
