package com.bitcamp.sc.member.controller;

import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitcamp.sc.member.service.AuthNumberService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthNumberController {

    private final AuthNumberService service;

    // 인증번호가 사용자가 입력한 번호와 같은지 체크하는 메소드.
    @RequestMapping("/inquiry/pw/input-code")
    @ResponseBody
    public String CheckAuthNum(@RequestBody Map<String, Object> param) {
        String result = "N";
        Boolean checkedAuthNum = service.checkAuthNum((String) param.get("inputNum"), (String) param.get("userEmail"));
        System.out.println("checkedAuthNum : " + checkedAuthNum);

        //서비스클래스에서 인증번호를 보냈다면 문자열 Y를 이 메소드에서 반환. 그렇지 않으면 N을 반환하도록 한다.
        if (checkedAuthNum) {
            result = "Y";
        }
        System.out.println(result);
        return result;
    }
}
