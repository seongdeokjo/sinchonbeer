package com.bitcamp.sc.web.mypage.dto;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditMemberRequest {

	private long idx;
	private String newPw;
	private String phone;

	private String postcode;
	private String address1;
	private String address2;
	private long aidx;


	public Address toAddress(){
		return Address.builder()
				.postcode(this.postcode)
				.address1(this.address1)
				.address2(this.address2)
				.idx(this.aidx)
				.build();
	}

}