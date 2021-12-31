package com.bitcamp.sc.member.domain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberAddressRequest {

	private String postcode;
	private String address1;
	private String address2;
	
	public MemberAddress toMemberAddress() {
		System.out.println("toMemberAddress() 메소드 진입 성공");
		return new MemberAddress(0,0, postcode, address1, address2);
	}
}
