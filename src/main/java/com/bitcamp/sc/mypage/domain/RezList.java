package com.bitcamp.sc.mypage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RezList {
	private int pidx; // 예약 번호로 변경할 것
	private int pprice;
	private String pway;
	private int tpeople; // orders table
	private String tdate;
}
