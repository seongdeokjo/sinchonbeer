package com.bitcamp.sc.domain.member.service;


import com.bitcamp.sc.domain.mypage.domain.UpdateMember;

public interface MemberEditService {

    //회원 수정 에서 기존 비밀번호 확인하기 -- 회원 삭제와 메서드 중복..
     String getPw(long idx);
    // 회원 정보 수정
     int updateMember(UpdateMember member) ;
    // 회원 정보 조회
     UpdateMember getMemberInfo(long idx);
}