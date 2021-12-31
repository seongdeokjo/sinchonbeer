package com.bitcamp.sc.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class RegRequest {

	private MemberRegRequest memberRegRequest;
	private MemberAddressRequest memberAddressRequest;
}
