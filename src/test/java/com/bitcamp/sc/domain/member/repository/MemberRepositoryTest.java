package com.bitcamp.sc.domain.member.repository;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.address.repository.AddressDao;
import com.bitcamp.sc.domain.member.domain.*;

import com.bitcamp.sc.domain.mypage.repository.MypageDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    SqlSessionTemplate template;
    @Autowired
    MemberDao memberDao;
    @Autowired
    MypageDao mypageDao;
    @Autowired
    AddressDao addressDao;

    @Test
    void 회원주소_저장(){
        String email = "test1@naver.com";
        String pw = "1234";
        String name = "테스트";
        String phone = "01012345678";
        String postCode = "01";
        String address1 = "서울";
        String address2 = "1";
        //given
        memberDao.save(Member.builder()
                .email(email)
                .name(name)
                .pw(pw)
                .phone(phone)
                .build());
        Member findMember = memberDao.findByEmail(email);
        log.info("member = {}",findMember);

        addressDao.save(Address.builder()
                .member(findMember)
                .postcode(postCode)
                .address1(address1)
                .address2(address2)
                .build());


    }

}