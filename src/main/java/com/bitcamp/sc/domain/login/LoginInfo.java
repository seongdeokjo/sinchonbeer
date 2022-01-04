package com.bitcamp.sc.domain.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {

	private int idx;
	private String email;
	private String name;
	private String phone;
	
}
