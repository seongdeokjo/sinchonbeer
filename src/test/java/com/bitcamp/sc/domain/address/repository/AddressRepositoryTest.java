package com.bitcamp.sc.domain.address.repository;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
public class AddressRepositoryTest {
    @Autowired
    SqlSessionTemplate template;
    @Autowired
    MemberDao memberDao;
    @Autowired
    AddressDao addressDao;

    @Test
    @Transactional
    void 회원과주소_저장() {
        String email = "test2@naver.com";
        String pw = "1234";
        String name = "테스트";
        String phone = "01012345678";
        String postCode = "01";
        String address1 = "서울";
        String address2 = "1";

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
        Address findAddress = addressDao.findAddressByMidx(findMember.getIdx());
        log.info("address = {}", findAddress);

        Assertions.assertThat(findAddress.getAddress1()).isEqualTo(address1);

    }


}
