package com.bitcamp.sc.domain.mypage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderList {
	private int pidx;
	private int pprice;
	private String pdate;
	private String pstatus;
	private int oidx;
	private int amount;
	private String gtitle;
	private String gname;
	private String gphoto;
}
