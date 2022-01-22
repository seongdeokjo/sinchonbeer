package com.bitcamp.sc.web.address.dto;

import com.bitcamp.sc.domain.address.domain.Address;
import lombok.Data;

@Data
public class MemberAddress {
    private long aidx;
    private String postcode;
    private String address1;
    private String address2;

    public MemberAddress(Address entity) {
        this.aidx = entity.getIdx();
        this.postcode = entity.getPostcode();
        this.address1 = entity.getAddress1();
        this.address2 = entity.getAddress2();
    }
}
