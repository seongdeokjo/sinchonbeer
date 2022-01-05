package com.bitcamp.sc.domain.member.domain;

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
public class MemberRegRequest {

	private String email;
	private String pw;
	private String name;
	private String phone;

	public Member toMember() {
		return new Member(0,this.email, this.pw, this.name, this.phone, null);
	}
}