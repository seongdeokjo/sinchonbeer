package com.bitcamp.sc.domain.member.service;

import com.bitcamp.sc.domain.member.domain.*;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class MemberRegServiceTest {
    @Autowired
    SqlSessionTemplate template;
    @Autowired
    MemberRegService service;

    MemberDao dao;

    @Test
    @Transactional
    void regMember() {
        //given
        MemberRegRequest memberRegRequest = new MemberRegRequest();

        memberRegRequest.setEmail("test1234@naver.com");
        memberRegRequest.setPw("1q2w3e4r!!");
        memberRegRequest.setName("테스트1");
        memberRegRequest.setPhone("01011111111");

        dao = template.getMapper(MemberDao.class);
        Member member = memberRegRequest.toMember();
        int result = dao.insertMember(member);

        assertThat(result).isEqualTo(1);
        log.info("member ={}",member);
    }

    @Test
    @Transactional
    void regAddress() {
        //given
        MemberRegRequest memberRegRequest = new MemberRegRequest();
        MemberAddressRequest addressRequest = new MemberAddressRequest();

        memberRegRequest.setEmail("test1234@naver.com");
        memberRegRequest.setPw("1q2w3e4r!!");
        memberRegRequest.setName("테스트1111");
        memberRegRequest.setPhone("01011111111");

        addressRequest.setAddress1("제주");
        addressRequest.setAddress2("1동");
        addressRequest.setPostcode("2222");

        MemberAddress address = addressRequest.toMemberAddress();
        boolean resultA = address.formValidate();


        // then
        dao = template.getMapper(MemberDao.class);
        Member member = memberRegRequest.toMember();
        int result = dao.insertMember(member);
        log.info("memberId ={}", member.getIdx());

        int resultAddress = 0;
        if (resultA) {
            address.setMidx(member.getIdx());
            resultAddress = dao.insertAddress(address);
        }

        // when
        assertThat(result).isEqualTo(1);
        log.info("member ={}", member);
        assertThat(resultAddress).isEqualTo(1);
        log.info("address = {}", address);
    }


}