package com.bitcamp.sc.domain.member.domain;

import com.bitcamp.sc.web.login.dto.LoginInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private long idx;
	private String email;
	private String pw;
	private String name;
	private String phone;
	private String code;
//	private MemberType role;

	public Member(String email, String pw, String name, String phone) {
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
	}

	public LoginInfo toLoginInfo() {
		return new LoginInfo(this.idx, this.email, this.name, this.phone);
	}
}