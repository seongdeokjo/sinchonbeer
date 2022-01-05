package com.bitcamp.sc.domain.member.service;

import com.bitcamp.sc.domain.member.domain.*;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import com.bitcamp.sc.web.member.dto.RegRequest;
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
class MemberRegServiceTest {
    @Autowired
    SqlSessionTemplate template;
    @Autowired
    MemberRegService service;

    MemberDao dao;

    @Test
    void regMember() {
        //given
        RegRequest regRequest = new RegRequest();

        regRequest.setEmail("test1234@naver.com");
        regRequest.setPw("1q2w3e4r!!");
        regRequest.setName("테스트1");
        regRequest.setPhone("01011111111");

        dao = template.getMapper(MemberDao.class);
        Member member = regRequest.toMember();
        int result = dao.saveMember(member);

        assertThat(result).isEqualTo(1);
        log.info("member ={}",member);
    }

    @Test
    void regAddress() {
        //given
        RegRequest regRequest = new RegRequest();

        regRequest.setEmail("test1234@naver.com");
        regRequest.setPw("1q2w3e4r!!");
        regRequest.setName("테스트1111");
        regRequest.setPhone("01011111111");

        regRequest.setAddress1("제주");
        regRequest.setAddress2("1동");
        regRequest.setPostcode("2222");

        Address address = regRequest.toMemberAddress();
        boolean resultA = address.formValidate();


        // then
        dao = template.getMapper(MemberDao.class);
        Member member = regRequest.toMember();
        int result = dao.saveMember(member);
        log.info("memberId ={}", member.getIdx());

        int resultAddress = 0;
        if (resultA) {
            address.setMidx(member.getIdx());
            resultAddress = dao.saveAddress(address);
        }

        // when
        assertThat(result).isEqualTo(1);
        log.info("member ={}", member);
        assertThat(resultAddress).isEqualTo(1);
        log.info("address = {}", address);
    }


}