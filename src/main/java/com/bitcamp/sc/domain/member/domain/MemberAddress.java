package com.bitcamp.sc.domain.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberAddress {

	private int aidx;
	private int midx;
	private String postcode;
	private String address1;
	private String address2;

	// 주소를 모두 입력하였는지 확인하는 메소드
	public boolean formValidate() {
		System.out.println("formValidate()메소드 진입 성공");
		boolean check = false;
		// 만일 우편번호(필수), 주소(필수), 상세주소(선택)가 입력 되었다면 check는 true, 아니면 false를 반환
		if (postcode != null && address1 != null || address2 != null) {
			if (!(postcode.trim().isEmpty() || address1.trim().isEmpty() || address2.trim().isEmpty())) {
				check = true;
			}
		}
		System.out.println("=======주소 입력 정보=======");
		System.out.println("입력한 postcode :" + postcode);
		System.out.println("입력한 address1 :" + address1);
		System.out.println("입력한 address2 :" + address2);
		System.out.println("=======================");
		return check;
	}
}