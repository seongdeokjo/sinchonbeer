package com.bitcamp.sc.domain.mypage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMember {
	private String midx;
	private String memail;
	private String mname;
	private String mnewPw;
	private String mphone;
	private String postcode;
	private String address1;
	private String address2;
}
