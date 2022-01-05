package com.bitcamp.sc.web.member.controller;

import com.bitcamp.sc.domain.member.service.MemberVerifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/members/check")
@RequiredArgsConstructor
public class MemberVerifyController {

    private final MemberVerifyService verifyService;

    // 이메일 중복 검사
    @PostMapping("/email")
    public String checkEmail(@RequestBody String email){
                                                        // @ 변환
       return verifyService.checkEmail(email.replace("%40", "@"));
    }

    // 인증번호가 사용자가 입력한 번호와 같은지 체크하는 메소드.
    @PostMapping("/code")
    public String checkCode(@RequestBody Map<String, Object> param) {
        String result = "N";
        Boolean checkedAuthNum = verifyService.checkCode((String) param.get("inputNum"), (String) param.get("userEmail"));
        System.out.println("checkedAuthNum : " + checkedAuthNum);

        //서비스클래스에서 인증번호를 보냈다면 문자열 Y를 이 메소드에서 반환. 그렇지 않으면 N을 반환하도록 한다.
        if (checkedAuthNum) {
            result = "Y";
        }
        System.out.println(result);
        return result;
    }
}