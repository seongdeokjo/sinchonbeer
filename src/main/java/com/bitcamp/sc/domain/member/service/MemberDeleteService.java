package com.bitcamp.sc.domain.member.service;

import com.bitcamp.sc.domain.member.repository.MemberDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberDeleteService {
    private final SqlSessionTemplate template;

    // 회원 탈퇴
    public void deleteMember(long idx) {
        template.getMapper(MemberDao.class).deleteMember(idx);
    }

    //회원 수정 에서 기존 비밀번호 확인하기
    public String getPw(long idx) {
        return template.getMapper(MemberDao.class).findByMidx(idx).getPw();
    }
}