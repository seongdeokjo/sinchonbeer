package com.bitcamp.sc.domain.member.service;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.address.repository.AddressDao;
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

    MemberDao mdao;
    AddressDao adao;

    @Test
    void regMember() {
        //given
        RegRequest regRequest = new RegRequest();

        regRequest.setEmail("test1234@naver.com");
        regRequest.setPw("1q2w3e4r!!");
        regRequest.setName("테스트1");
        regRequest.setPhone("01011111111");

        mdao = template.getMapper(MemberDao.class);
        Member member = regRequest.toMember();
        mdao.save(member);

        assertThat(member.getEmail()).isEqualTo(regRequest.getEmail());
        log.info("member ={}",member);
    }

    @Test
    void 회원과주소_저장() {
        //given
        mdao = template.getMapper(MemberDao.class);
        adao = template.getMapper(AddressDao.class);
        RegRequest regRequest = new RegRequest();

        regRequest.setEmail("test1234@naver.com");
        regRequest.setPw("1q2w3e4r!!");
        regRequest.setName("테스트1111");
        regRequest.setPhone("01011111111");

        regRequest.setAddress1("제주");
        regRequest.setAddress2("1동");
        regRequest.setPostcode("2222");

        Address address = regRequest.toAddress();
        boolean resultA = address.formValidate();


        // then

        Member member = regRequest.toMember();
        mdao.save(member);
        log.info("memberId ={}", member.getIdx());


        saveAddress(address, member);

        // when
        assertThat(member.getEmail()).isEqualTo(regRequest.getEmail());
        log.info("member ={}", member);
        assertThat(address.getPostcode()).isEqualTo(regRequest.getPostcode());
        log.info("address = {}", address);
    }

    private void saveAddress(Address address, Member member) {
        if (member != null) {
            address.setMidx(member.getIdx());
            adao.save(address);
        }
    }

}