package com.bitcamp.sc.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.member.repository.MemberDao;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberFindService {

    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;

    // 이름+전화번호로 이메일 찾기 --> 매개변수 값이 동일할 경우 = ?
    public String getEmail(String name, String phone) {
        String email = null;
        Member member = memberDao.findByNameAndPhone(name, phone);
        if (member != null) {
            email = member.getEmail();
        }
        return email;
    }

    // 이름 + 이메일로 계정 검색(비밀번호 찾기위한 용도)
    public String getEmailForPw(String name, String email) {
        String resultEmail = null;
        try {
            resultEmail = memberDao.findByNameAndEmail(name, email).getEmail();
        } catch (NullPointerException e) {
            e.printStackTrace();
            log.info("찾으려는 이메일이 없을 경우 발생하는 예외");
        }
        return resultEmail;
    }

    // 비밀번호 재설정
    public Boolean modifyPw(String userEmail, String newPw) {

        Boolean result = false;
        int resultCnt = memberDao.updatePw(userEmail, passwordEncoder.encode(newPw));
        if (resultCnt == 1) {
            result = true;
        }
        return result;
    }
}