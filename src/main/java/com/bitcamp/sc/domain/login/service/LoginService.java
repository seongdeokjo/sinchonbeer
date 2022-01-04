package com.bitcamp.sc.domain.login.service;

import com.bitcamp.sc.domain.login.LoginForm;
import com.bitcamp.sc.domain.login.LoginInfo;
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
public class LoginService {
    private final SqlSessionTemplate template;
    private final PasswordEncoder passwordEncoder;

    private MemberDao memberDao;

    public LoginInfo login(LoginForm form) {
        LoginInfo info = new LoginInfo();
        memberDao = template.getMapper(MemberDao.class);
        Member member = memberDao.selectByEmail(form.getEmail());
        log.info("member={}", member);
        if (member != null && passwordEncoder.matches(form.getPw(), member.getPw())) {
            info = member.toLoginInfo();
        }
        return info;
    }
}