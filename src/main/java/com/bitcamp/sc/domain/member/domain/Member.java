package com.bitcamp.sc.domain.member.domain;

import com.bitcamp.sc.web.login.dto.LoginInfo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {

	private long idx;
	private String email;
	private String pw;
	private String name;
	private String phone;
	private String code;
//	private MemberType role;

	@Builder
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