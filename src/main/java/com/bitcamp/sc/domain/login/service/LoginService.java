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
    private final SqlSessionTemplate template;
    private final PasswordEncoder passwordEncoder;

    private MemberDao memberDao;

    public LoginInfo login(LoginForm form) {
        LoginInfo info = new LoginInfo();
        memberDao = template.getMapper(MemberDao.class);
        Member member = memberDao.findByEmailAndPw(form.getEmail(), form.getPw());
        log.info("member={}", member);
        if (member != null && passwordEncoder.matches(form.getPw(), member.getPw())) {
            info = member.toLoginInfo();
        }
        return info;
    }
}