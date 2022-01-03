package com.bitcamp.sc.web.login;

import com.bitcamp.sc.domain.login.Login;
import com.bitcamp.sc.domain.login.service.LoginServiceV2;
import com.bitcamp.sc.domain.login.service.LoginServiceV3;
import com.bitcamp.sc.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginControllerV3 {

    private final LoginServiceV3 serviceV3;


    @GetMapping("/test")
    public String loginForm(@ModelAttribute("login") Login login,
                            Model model,
                            @RequestHeader(value = "referer", required = false) String redirectUri,
                            @CookieValue(value = "cookie",required = false) String cookie) {
        model.addAttribute("redirectUri", redirectUri);
        model.addAttribute("cookie",cookie);
        return "member/loginForm2";
    }

    @PostMapping("/test")
    public String login(@Valid @ModelAttribute Login login,
                        BindingResult bindingResult,
                        Model model,
                        HttpSession session,
                        HttpServletResponse response,
                        HttpServletRequest request) {
        log.info("request={}",request.getRequestURL().toString());
        log.info("login.getRedirectUri() == request => {}",login.getRedirectUri().equals(request.getRequestURL().toString()) );
        log.info("login ={}", login);
        if (bindingResult.hasErrors()) {
            return "member/loginForm2";
        }

        Member member  = serviceV3.login(login);
        if(member == null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "member/loginForm2";
        }
        // 세션 저장 및 성공 처리 로직 추가하기

        if(member != null){
            return "redirect:" + login.getRedirectUri();
        }

        return "redirect:test";
    }
}
