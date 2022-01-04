package com.bitcamp.sc.web.login;

import com.bitcamp.sc.domain.login.LoginForm;
import com.bitcamp.sc.domain.login.service.LoginService;
import com.bitcamp.sc.domain.login.LoginInfo;
import com.bitcamp.sc.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService service;

    @GetMapping
    public String loginForm(@ModelAttribute("loginForm") LoginForm form,
                            @CookieValue(value = "cookie", required = false) Cookie cookie) {
        // 테스트
        if (cookie != null) {
            form.setEmail(cookie.getValue());
            form.setReEmail(true);
        }
        return "login/loginForm";
    }

    @PostMapping
    public String login(@Valid @ModelAttribute LoginForm form,
                        BindingResult bindingResult,
                        HttpSession session,
                        HttpServletResponse response,
                        HttpServletRequest request) {
        log.info("request={}", request.getRequestURL().toString());
        log.info("loginForm ={}", form);
        if (bindingResult.hasErrors()) {
            log.info("errorcount= {}", bindingResult.getFieldErrorCount());
            return "login/loginForm";
        }

        LoginInfo info = service.login(form);
        if (info == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        // 세션 저장 및 성공 처리 로직 추가하기
        session.setAttribute(SessionConst.LOGIN_MEMBER, info);
        String dest = (String) session.getAttribute("dest");
        extractedCookie(form, response);
        return dest != null ? "redirect:" + dest : "redirect:/";
    }

    private void extractedCookie(LoginForm form, HttpServletResponse response) {
        if (form.isReEmail()) {
            // 이메일 기억하기 쿠키
            Cookie cookie = new Cookie("cookie", form.getEmail());
            // 기억하기 체크하면 쿠키에 저장
            cookie.setPath("/");
            // 30분
            cookie.setMaxAge(60 * 30);
            response.addCookie(cookie);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
