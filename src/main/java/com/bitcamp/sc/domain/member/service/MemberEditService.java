package com.bitcamp.sc.domain.member.service;

import com.bitcamp.sc.domain.member.repository.MemberDao;
import com.bitcamp.sc.domain.mypage.domain.UpdateMember;
import com.bitcamp.sc.domain.mypage.repository.MypageDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberEditService {
    private final SqlSessionTemplate template;
    private final PasswordEncoder passwordEncoder;

    //회원 수정 에서 기존 비밀번호 확인하기 -- 회원 삭제와 메서드 중복..
    public String getPw(long idx) {
        return template.getMapper(MemberDao.class).findByMidx(idx).getPw();
    }

    // 회원 정보 수정
    public int updateMember(UpdateMember member) {
        String securityPw = passwordEncoder.encode(member.getMnewPw());
        member.setMnewPw(securityPw);

        return template.getMapper(MypageDao.class).updateMember(member);
    }

    // 회원 정보 조회
    public UpdateMember getMemberInfo(long idx) {
        return template.getMapper(MypageDao.class).getMemberInfo(idx);
    }
}