package com.bitcamp.sc.domain.member.service;

import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import com.bitcamp.sc.web.member.dto.RegRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
public class MemberDeleteServiceTest {
    @Autowired
    SqlSessionTemplate template;

    @Test
    void 회원_삭제() {
        //given
        RegRequest regRequest = new RegRequest();

        regRequest.setEmail("test1234@naver.com");
        regRequest.setPw("1q2w3e4r!!");
        regRequest.setName("테스트1");
        regRequest.setPhone("01011111111");

        Member member = regRequest.toMember();
        template.getMapper(MemberDao.class).save(member);
        log.info("member = {}",member);
        long memgerId = member.getIdx();

        template.getMapper(MemberDao.class).deleteMember(member.getIdx());

        Member byMidx = template.getMapper(MemberDao.class).findByMidx(memgerId);
        log.info("member = {}",byMidx);
        assertThat(byMidx).isNull();

    }
}
