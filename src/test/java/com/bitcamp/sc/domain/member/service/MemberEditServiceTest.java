package com.bitcamp.sc.domain.member.service;

import com.bitcamp.sc.domain.address.repository.AddressDao;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import com.bitcamp.sc.domain.mypage.repository.MypageDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
public class MemberEditServiceTest {

    @Autowired
    SqlSessionTemplate template;

    @Autowired
    MemberDao memberDao;
    @Autowired
    AddressDao addressDao;
    @Autowired
    MypageDao mypageDao;

    @Test
    void 회원_수정(){

    }

}
