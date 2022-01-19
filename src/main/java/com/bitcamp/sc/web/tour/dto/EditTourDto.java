package com.bitcamp.sc.web.tour.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
public class EditTourDto {
	@Max(4)
	private int tourPeople;
	@NotNull
	private String resDate;
	@NotNull
	private String newDate;
}
