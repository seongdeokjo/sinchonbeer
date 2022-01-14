package com.bitcamp.sc.domain.member.repository;

import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.web.login.dto.LoginForm;
import org.apache.ibatis.annotations.Param;

public interface MemberDao {
    //회원가입
    void save(Member member);

    // 이메일 + 비밀번호로 로그인 정보 가져오기
    Member findByEmailAndPw(LoginForm form);

    //이메일로 회원정보 불러오기
    Member findByEmail(String email);

    //이메일 찾기 (멤버 객체)
    Member findByNameAndPhone(String name, String phone);

    //비밀번호 찾기 (멤버 객체)
    Member findByNameAndEmail(String name, String email);

    //멤버의 idx로 멤버 정보 조회하기
    Member findByMidx(long idx);

    //비밀번호 찾기 - 인증번호 저장하기.
    int updateCode(@Param("code") String code, @Param("email") String email);

    //비밀번호 찾기 - 비밀번호 재설정
    int updatePw(@Param("email") String email, @Param("pw") String pw);

    // 회원 탈퇴
    void deleteMember(long idx);
}