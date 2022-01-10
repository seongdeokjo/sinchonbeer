package com.bitcamp.sc.web.mypage.dto;

import com.bitcamp.sc.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EditMemberResponse {
    private long idx;
    private String email;
    private String name;
    private String phone;
    private String postcode;
    private String address1;
    private String address2;

    @Builder
    public EditMemberResponse(Member fromMember){
        this.idx = fromMember.getIdx();
        this.email = fromMember.getEmail();
        this.name = fromMember.getName();
        this.phone = fromMember.getPhone();
        this.postcode = fromMember.getAddress().getPostcode();
        this.address1 = fromMember.getAddress().getAddress1();
        this.address2 = fromMember.getAddress().getAddress2();
    }
}
