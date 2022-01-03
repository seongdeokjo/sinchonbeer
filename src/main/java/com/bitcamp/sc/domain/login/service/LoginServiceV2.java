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
public class LoginServiceV2 {
	private final SqlSessionTemplate template;
	private final PasswordEncoder passwordEncoder;

	private MemberDao memberDao;

	public boolean login(Login login, HttpSession session, HttpServletResponse response) {
		// 로그인체크 기본 값은 false
		boolean loginChk = false;
		// MemberDao를 클래스로 만들어 구현
		memberDao = template.getMapper(MemberDao.class);
		try {
			Member member = memberDao.selectByEmail(login.getEmail());
			log.info("암호화 결과 : {}",passwordEncoder.matches(login.getPw(), member.getPw()));

			// 회원이 로그인 했다면 session 유지 시작, 로그인체크는 true로.
			log.info("member={}",member);
			if (member != null && passwordEncoder.matches(login.getPw(), member.getPw())) {
				session.setAttribute("loginInfo", member.toLoginInfo());
				loginChk = true;
			}
		} catch (NullPointerException e) {
			log.info("[LoginService] NullPointerException 예외 발생. - 일치하는 이메일과 비밀번호 계정이 없음.");
		}
		extractedCookie(login, response);
		return loginChk;
	}

	private void extractedCookie(Login login, HttpServletResponse response) {
		// 이메일 기억하기 쿠키
		Cookie cookie = new Cookie("cookie", login.getEmail());
		if (login.isReEmail()) {
			// 기억하기 체크하면 쿠키에 저장
			cookie.setPath("/");
			// 30분
			cookie.setMaxAge(60 * 30);
			response.addCookie(cookie);
		} else {
			// 기억하기 체크 해제하면 쿠키에 저장 안함.
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
}