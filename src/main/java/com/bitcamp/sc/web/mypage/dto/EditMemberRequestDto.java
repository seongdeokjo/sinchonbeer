package com.bitcamp.sc.web.mypage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EditMemberRequestDto {

	private String name;
	private String newPw;
	private String phone;
	private String postcode;
	private String address1;
	private String address2;
}
