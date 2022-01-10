package com.bitcamp.sc.web.member.dto;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.member.domain.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class RegRequest {

    private String email;
    private String pw;
    private String name;
    private String phone;

    private String postcode;
    private String address1;
    private String address2;

    @Builder
    public RegRequest(String email, String pw, String name, String phone, String postcode, String address1, String address2) {
        this.email = email;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.postcode = postcode;
        this.address1 = address1;
        this.address2 = address2;
    }

    public Member toMember() {
        return Member.builder()
                .name(name)
                .email(email)
                .pw(pw)
                .phone(phone)
                .build();
    }

    public Address toAddress() {
        return Address.builder()
                .postcode(postcode)
                .address1(address1)
                .address2(address2)
                .build();
    }
}