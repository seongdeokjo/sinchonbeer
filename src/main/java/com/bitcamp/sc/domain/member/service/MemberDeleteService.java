package com.bitcamp.sc.domain.member.service;

import com.bitcamp.sc.domain.member.repository.MemberDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberDeleteService {
    private final MemberDao memberDao;

    // 회원 탈퇴
    public void deleteMember(long idx) {
        memberDao.deleteMember(idx);
    }

    //회원 수정 에서 기존 비밀번호 확인하기
    public String getPw(long idx) {
        return memberDao.findByMidx(idx).getPw();
    }
}