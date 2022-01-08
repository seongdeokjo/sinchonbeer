package com.bitcamp.sc.domain.member.service.impl;

import com.bitcamp.sc.domain.member.repository.MemberDao;
import com.bitcamp.sc.domain.member.service.MemberEditService;
import com.bitcamp.sc.domain.mypage.domain.UpdateMember;
import com.bitcamp.sc.domain.mypage.repository.MypageDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberEditServiceImpl implements MemberEditService {
    private final PasswordEncoder passwordEncoder;
    private final MypageDao mypageDao;
    private final MemberDao memberDao;

    //회원 수정 에서 기존 비밀번호 확인하기 -- 회원 삭제와 메서드 중복..
    public String getPw(long idx) {
        return memberDao.findByMidx(idx).getPw();
    }

    // 회원 정보 수정
    public int updateMember(UpdateMember member) {
        String securityPw = passwordEncoder.encode(member.getNewPw());
        member.setNewPw(securityPw);
        return mypageDao.updateMember(member);
    }

    // 회원 정보 조회
    public UpdateMember getMemberInfo(long idx) {
        return mypageDao.getMemberInfo(idx);
    }
}