package com.bitcamp.sc.web.member.dto;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.member.domain.Member;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegRequest {

    private String email;
    private String pw;
    private String name;
    private String phone;

    private String postcode;
    private String address1;
    private String address2;


    public Member toMember() {
        return new Member(this.email, this.pw, this.name, this.phone);
    }

    public Address toAddress() {
        return new Address(0, postcode, address1, address2);
    }
}