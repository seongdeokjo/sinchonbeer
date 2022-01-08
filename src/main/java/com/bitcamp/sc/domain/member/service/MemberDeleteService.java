package com.bitcamp.sc.domain.member.service;

public interface MemberDeleteService {
    // 회원 탈퇴
    void deleteMember(long idx);

    //회원 수정 에서 기존 비밀번호 확인하기
    String getPw(long idx);
}