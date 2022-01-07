package com.bitcamp.sc.domain.address.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

    private long idx;
    private long midx;
    private String postcode;
    private String address1;
    private String address2;

    public Address(long midx, String postcode, String address1, String address2) {
        this.midx = midx;
        this.postcode = postcode;
        this.address1 = address1;
        this.address2 = address2;
    }

    // 주소를 모두 입력하였는지 확인하는 메소드
    public boolean formValidate() {
        boolean check = false;
        // 우편번호(필수), 주소1(필수) -> true
        if ((postcode != null || postcode.trim().isEmpty()) && (address1 != null || address1.trim().isEmpty())) {
            check = true;
        }
        return check;
    }
}