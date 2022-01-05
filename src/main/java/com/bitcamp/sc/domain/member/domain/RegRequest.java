package com.bitcamp.sc.domain.member.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegRequest {
	private MemberRegRequest memberRegRequest;
	private MemberAddressRequest memberAddressRequest;
}