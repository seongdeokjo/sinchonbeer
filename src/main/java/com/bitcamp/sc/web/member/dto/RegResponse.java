package com.bitcamp.sc.web.member.dto;


import lombok.*;

@Getter
@Setter
@ToString
public class RegResponse {

    private String email;
    private String name;
    private String phone;

    private String postcode;
    private String address1;
    private String address2;


}
