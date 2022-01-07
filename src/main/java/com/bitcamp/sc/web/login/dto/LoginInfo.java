package com.bitcamp.sc.web.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
	private long idx;
	private String email;
	private String name;
	private String phone;
}