package com.bitcamp.sc.domain.member.service.impl;

import com.bitcamp.sc.domain.member.service.MemberRegService;
import com.bitcamp.sc.web.member.dto.RegRequest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberRegServiceImplTest {
    @Autowired
    MemberRegService regService;

    @Test
    @Transactional
    void 회원과주소_저장_서비스(){
        RegRequest request = RegRequest.builder()
                .email("test@naver.com")
                .pw("1234")
                .name("test")
                .phone("010")
                .postcode("10")
                .address1("서울")
                .address2("1-1")
                .build();

        boolean result = regService.saveMember(request);
        Assertions.assertThat(result).isTrue();

    }
}