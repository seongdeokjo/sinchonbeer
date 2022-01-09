package com.bitcamp.sc.domain.member.repository;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.address.repository.AddressDao;
import com.bitcamp.sc.domain.member.domain.*;

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

    @Test
    void 회원_저장_찾기() {
        String name ="테스트";
        String email = "test@naver.com";
        //given
        memberDao.save(Member.builder()
                .email(email)
                .name(name)
                .pw("1234")
                .phone("01012341234")
                .build());

        Member findMember = memberDao.findByEmail(email);
        log.info("member ={}", findMember);
        assertThat(findMember.getEmail()).isEqualTo(email);
    }

}