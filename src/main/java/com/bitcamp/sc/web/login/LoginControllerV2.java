package com.bitcamp.sc.web.login;

import com.bitcamp.sc.domain.login.Login;
import com.bitcamp.sc.domain.login.service.LoginServiceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginControllerV2 {

    private final LoginServiceV2 serviceV2;


    @GetMapping("/test")
    public String loginForm(@ModelAttribute("login") Login login,
                            Model model,
                            @RequestHeader(value = "referer", required = false) String redirectUri,
                            @CookieValue(value = "cookie",required = false) String cookie) {
        model.addAttribute("redirectUri", redirectUri);
        model.addAttribute("cookie",cookie);
        log.info("uri1 ={}", redirectUri);
        return "member/loginForm2";
    }

    @PostMapping("/test")
    public String login(@ModelAttribute Login login,
                        Model model,
                        HttpSession session,
                        HttpServletResponse response,
                        HttpServletRequest request) {
        log.info("request={}",request.getRequestURL().toString());
        log.info("login.getRedirectUri() == request => {}",login.getRedirectUri().equals(request.getRequestURL().toString()) );
        log.info("login ={}", login);
        boolean check = serviceV2.login(login, session, response);
        if(check &&  login.getRedirectUri().equals(request.getRequestURL().toString())){
            return "redirect:/";
        }
        if(check){
            return "redirect:" + login.getRedirectUri();
        }

        return "redirect:test";
    }
}
