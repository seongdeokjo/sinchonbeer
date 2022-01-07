package com.bitcamp.sc.web.member.controller;

import javax.servlet.http.HttpSession;

import com.bitcamp.sc.domain.member.service.MemberDeleteService;
import com.bitcamp.sc.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bitcamp.sc.web.login.dto.LoginInfo;
import com.bitcamp.sc.domain.member.service.MemberService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberDeleteController {
    private final MemberDeleteService service;
    private final PasswordEncoder passwordEncoder;

    // 회원 탈퇴 페이지 이동
    @GetMapping("/mypage/delete-id")
    public String deleteIdGet() {
        return "mypage/delete-id";
    }

    // 회원 탈퇴 실행
    @PostMapping("/mypage/delete-id")
    public String deleteIdPost(HttpSession session) {
        LoginInfo login = (LoginInfo) session.getAttribute(SessionConst.LOGIN_MEMBER);
        service.deleteMember(login.getIdx());
        // 세션 초기화
        session.invalidate();
        return "redirect:/main";
    }

    // 비밀번호 DB 일치 여부 확인
    @PostMapping("/pwCheckDelete")
    @ResponseBody
    public String pwCheckPost(@RequestBody String pw, HttpSession session) {
        LoginInfo login = (LoginInfo) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (passwordEncoder.matches(pw, service.getPw(login.getIdx()))) {
            return "Y";
        }
        return "N";
    }
}