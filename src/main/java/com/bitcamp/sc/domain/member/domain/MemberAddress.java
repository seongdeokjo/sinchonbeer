package com.bitcamp.sc.domain.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        boolean check = false;
        // 우편번호(필수), 주소1(필수) -> true
        if ((postcode != null || postcode.trim().isEmpty()) && (address1 != null || address1.trim().isEmpty())) {
            check = true;
        }
        System.out.println("=======주소 입력 정보=======");
        System.out.println("입력한 postcode :" + postcode);
        System.out.println("입력한 address1 :" + address1);
        System.out.println("입력한 address2 :" + address2);
        System.out.println("=======================");
        return check;
    }
}