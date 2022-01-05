package com.bitcamp.sc.web.member.controller;

import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitcamp.sc.domain.member.service.MemberEmailService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberMailController {

    private final MemberEmailService service;

    // 메일 전송시 지연 발생
    //인증번호 메일로 전송하기 - ajax로 처리 (인증번호를 메일로 보내는 작업)
    @RequestMapping(value = "/inquiry/pw/auth", method = RequestMethod.POST)
    @ResponseBody
    public String sendMail(@RequestBody Map<String, Object> param) {
        service.sendMail((String) param.get("userEmail"));
        return "ok";
    }
}
