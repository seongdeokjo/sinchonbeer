package com.bitcamp.sc.domain.member.service;

public interface MemberFindService {

    // 이름+전화번호로 이메일 찾기 --> 매개변수 값이 동일할 경우 = ?
    String getEmail(String name, String phone);

    // 이름 + 이메일로 계정 검색(비밀번호 찾기위한 용도)
     String getEmailForPw(String name, String email);

    // 비밀번호 재설정
     Boolean modifyPw(String userEmail, String newPw);
}