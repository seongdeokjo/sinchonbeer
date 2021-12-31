package com.bitcamp.sc.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.member.repository.MemberDao;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthNumberService {

    private final SqlSessionTemplate template;

    public Boolean checkAuthNum(String inputNum, String email) {

        Boolean result = false;
        String memberCode = template.getMapper(MemberDao.class).selectCode(email).getCode();

        if (memberCode.equals(inputNum)) {
            result = true;
        }
        System.out.println("일치한다면 true 반환 : " + result);
        return result;
    }
}