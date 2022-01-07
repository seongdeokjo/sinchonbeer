package com.bitcamp.sc.domain.mypage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMember {

	private String midx;
	private String email;
	private String name;
	private String newPw;
	private String phone;
	private String postcode;
	private String address1;
	private String address2;
}
