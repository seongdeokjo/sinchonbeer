package com.bitcamp.sc.domain.login.service;

import com.bitcamp.sc.domain.login.Login;
import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceV3 {
	private final SqlSessionTemplate template;
	private final PasswordEncoder passwordEncoder;

	private MemberDao memberDao;

	public Member login(Login login) {
		memberDao = template.getMapper(MemberDao.class);
		return memberDao.selectByEmail(login.getEmail())
				.filter(member -> passwordEncoder.matches(login.getPw(), member.getPw()))
				.orElse(null);
	}

//	private void extractedCookie(Login login, HttpServletResponse response) {
//		// 이메일 기억하기 쿠키
//		Cookie cookie = new Cookie("cookie", login.getEmail());
//		if (login.isReEmail()) {
//			// 기억하기 체크하면 쿠키에 저장
//			cookie.setPath("/");
//			// 30분
//			cookie.setMaxAge(60 * 30);
//			response.addCookie(cookie);
//		} else {
//			// 기억하기 체크 해제하면 쿠키에 저장 안함.
//			cookie.setPath("/");
//			cookie.setMaxAge(0);
//			response.addCookie(cookie);
//		}
//	}
}