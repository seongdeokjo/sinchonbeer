package com.bitcamp.sc.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class LoginInfo {

	private int idx;
	private String email;
	private String name;
	private String phone;
	
}
