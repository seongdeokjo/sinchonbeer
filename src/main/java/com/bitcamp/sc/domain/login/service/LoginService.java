package com.bitcamp.sc.domain.login.service;

import com.bitcamp.sc.web.login.dto.LoginForm;
import com.bitcamp.sc.web.login.dto.LoginInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final MemberDao memberDao;

    public LoginInfo login(LoginForm form) {

        Member member = memberDao.findByEmail(form.getEmail());
        log.info("member={}", member);
        return (member != null && passwordEncoder.matches(form.getPw(), member.getPw())) ? member.toLoginInfo() : null;
    }
}